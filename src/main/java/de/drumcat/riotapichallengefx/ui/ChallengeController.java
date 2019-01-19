package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.domain.Challenge;
import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import de.drumcat.riotapichallengefx.service.ClientStatsApiService;
import de.drumcat.riotapichallengefx.service.MatchStatsApiService;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.Match;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChallengeController {
    private final static Logger logger = LogManager.getLogger(ChallengeController.class);

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("test"); //name of persistence unit here
    EntityManager entityManager = factory.createEntityManager();

    public ChoiceBox<LANE> laneChoice;
    public ChoiceBox<QUEUE> queueChoice;
    public Button startButton;
    public Pane activeChallenge;
    public Pane startingChallenge;
    public LineChart<Number, Number> standingChart;
    public Label yourNameLabel;
    public Label opponentNameLabel;
    public Button forfeitButton;
    private Map<String, Challenge> runningChallenges = getChallengesFromDB();
    private Map<Long, Match> cachedMatches;
    private MatchStatsApiService matchStatsApiService;
    private XYChart.Series<Number, Number> pointsChallenger;
    private XYChart.Series<Number, Number> pointsOpponent;

    /**
     * Helper function to fill the points for a running challenger into a XYChart
     *
     * @param listOfStats list of points for the challenger
     */
    private void fillChart(List<Integer> listOfStats) {
        pointsChallenger.getData().clear();
        pointsChallenger.setName(yourNameLabel.getText());
        pointsOpponent.getData().clear();
        pointsOpponent.setName(opponentNameLabel.getText());
        int i = 1;

        for (Integer stat : listOfStats) {
            pointsChallenger.getData().add(new XYChart.Data<>(i, stat));
            // 10 possible points, so the points for the opponent are 10-challenger points
            pointsOpponent.getData().add(new XYChart.Data<>(i, 10 - stat));
            i++;
        }
    }

    /**
     * Helper function to start a new challenge.
     * Converts the visual choices in RIOT API constants
     * fills all relevant data and toogles the view
     */
    private void startChallenge() {
        int queue = 440;
        if (queueChoice.getValue().equals(QUEUE.RANKED_SOLO)) {
            queue = 420;
        }
        Challenge challenge = new Challenge(opponentNameLabel.getText(), yourNameLabel.getText(), queue,
                laneChoice.getValue().toString());
        challenge.setTimeStarted(1544439601880L); //TODO remove test value
//        challenge.setTimeStarted(System.currentTimeMillis());
        System.out.println("challenge = " + challenge);
        runningChallenges.put(opponentNameLabel.getText(), challenge);
        persistChallenge(challenge);
        refreshGames(challenge);
        fillChart(challenge.getResultPoints());
        showActiveChallenge(true);
        //TODO send a message to opponent
    }

    /**
     * Toogles the view between showing an active challenge
     * and  showing the startup dialog
     *
     * @param isChallengeActive true if the chart for an active challenger should be displayed
     */
    private void showActiveChallenge(boolean isChallengeActive) {
        startingChallenge.setVisible(!isChallengeActive);
        activeChallenge.setVisible(isChallengeActive);
    }

    /**
     * Switches the view to the challenge with a given user
     *
     * @param userName name of the user to show the challenge with
     */
    public void showUser(String userName) {
        opponentNameLabel.setText(userName);
        if (!runningChallenges.containsKey(userName)) {
            showActiveChallenge(false);
            return;
        }
        Challenge activeChallenge = runningChallenges.get(userName);
        fillChart(activeChallenge.getResultPoints());
        showActiveChallenge(true);
    }

    /**
     * Refreshes the gamedata for a given challenge.
     * Queries the RIOT API for matchdata
     *
     * @param challenge the challenge, that should be filled with match data
     */
    private void refreshGames(Challenge challenge) {
        List<ParticipantStatsTimeline> challengerData = getChallengeData(challenge.getChallengerName(), challenge);
        for (ParticipantStatsTimeline gameData : challengerData) {
            challenge.addChallengerGame(gameData);
        }

        List<ParticipantStatsTimeline> opponentData = getChallengeData(challenge.getOpponentName(), challenge);
        for (ParticipantStatsTimeline gameData : opponentData) {
            challenge.addOpponentsGame(gameData);
        }
        //TODO send message if challenge is over
        // TODO redraw chart
    }

    /**
     * Gets the statistics for given user and challenge
     *
     * @param username  the user name for which to gather the stats
     * @param challenge the challenge with all parameter to get the stats
     * @return a list with the statistics for given user and challenge
     */
    private List<ParticipantStatsTimeline> getChallengeData(String username, Challenge challenge) {
        List<ParticipantStatsTimeline> challengeData = new ArrayList<>();
        try {
            long startTime = challenge.getTimeStarted();
            int queue = challenge.getQueue();
            // get a list of all matches and filter them according to the parameter of the challenge
            MatchList matchListByUser = matchStatsApiService.getMatchListByUser(username, startTime, queue);
            List<MatchReference> filteredList = matchListByUser.getMatches().stream()
                    .filter(matchReference -> matchReference.getLane().equals(challenge.getPosition()))
                    .limit(10)
                    .collect(Collectors.toList());

            for (MatchReference matchReference : filteredList) {
                long gameId = matchReference.getGameId();
                // only read game data, if we have not done so before
                if (!cachedMatches.containsKey(gameId)) {
                    cachedMatches.put(gameId, matchStatsApiService.getMatchByMatchId(gameId));
                }
                Match matchByMatchId = cachedMatches.get(gameId);
                challengeData.add(matchStatsApiService.getGameStatsAndTimelineByUser(matchByMatchId, username));
            }
        } catch (RiotApiException e) {
            // if we couldn't retrieve the match data from the API, an empty list will be returned
            logger.warn(e.getMessage(), e);
        }
        return challengeData;
    }

    @FXML
    public void initialize() {
        // initialize chart
        pointsChallenger = new XYChart.Series<>();
        pointsOpponent = new XYChart.Series<>();
        standingChart.getData().add(pointsChallenger);
        standingChart.getData().add(pointsOpponent);
        // initialize api service and caching structures
        matchStatsApiService = new MatchStatsApiService();
        runningChallenges = new HashMap<>();
        cachedMatches = new HashMap<>();
        // initialize form with values
        laneChoice.getItems().addAll(LANE.values());
        laneChoice.setValue(LANE.JUNGLE);
        queueChoice.getItems().addAll(QUEUE.values());
        queueChoice.setValue(QUEUE.RANKED_SOLO);

        // get current user
        ClientStatsApiService clientApiService = new ClientStatsApiService();
        String currentUserName = clientApiService.getCurrentUser().getDisplayName();
        yourNameLabel.setText(currentUserName);
        opponentNameLabel.setText("Opponent");
        startButton.setOnAction(event -> startChallenge());
        forfeitButton.setOnAction(event -> cancelChallenge());
    }

    /**
     * Cancel an active challenge with the current selected opponent
     */
    private void cancelChallenge() {
        runningChallenges.remove(opponentNameLabel.getText());
        showActiveChallenge(false);
    }

    private enum LANE {
        MIDDLE,
        BOTTOM,
        JUNGLE,
        TOP,
        SUPPORT //TODO add difference between support and ADC
    }

    private enum QUEUE {
        RANKED_SOLO,
        RANKED_FLEX,
    }

    private Map<String, Challenge> getChallengesFromDB() {
        //Find all entries in challenge database
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Challenge> cq = cb.createQuery(Challenge.class);
        Root<Challenge> rootEntry = cq.from(Challenge.class);
        CriteriaQuery<Challenge> all = cq.select(rootEntry);
        TypedQuery<Challenge> allQuery = entityManager.createQuery(all);
        List<Challenge> resultList = allQuery.getResultList();

        Map<String, Challenge> challengeMap = new HashMap<>();
        if (resultList != null && !resultList.isEmpty()) {
            for (Challenge challenge : resultList) {
                challengeMap.put(challenge.getOpponentName(), challenge);
            }
        }
        return challengeMap;
    }

    private void persistChallenge(Challenge challenge){
        entityManager.getTransaction().begin();
        entityManager.persist(challenge);
        entityManager.getTransaction().commit();
    }
}
