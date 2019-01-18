package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.domain.Challenge;
import de.drumcat.riotapichallengefx.service.MatchStatsApiService;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import net.rithms.riot.api.endpoints.match.dto.MatchReference;

import java.util.*;
import java.util.stream.Collectors;

public class ChallengeControler {
    public ChoiceBox laneChoice;
    public ChoiceBox queueChoice;
    public Button buttME;
    public Pane activeChallenge;
    public Pane startingChallenge;
    public LineChart standingChart;
    public Label yourNameLabel;
    public Label enemyNameLabel;
    private Map<String, Challenge> runningChallenges;

    private void addSeriesToChart(List<Integer> listOfStats) {
        XYChart.Series<Number, Number> seriesME = new XYChart.Series<>();
        seriesME.setName(yourNameLabel.getText());
        XYChart.Series<Number, Number> seriesME2 = new XYChart.Series<>();
        seriesME2.setName(enemyNameLabel.getText());
        int i = 0;
        List<XYChart.Data<Number, Number>> lSeriesData = new ArrayList<>();
        List<XYChart.Data<Number, Number>> lSeriesData2 = new ArrayList<>();
        for (Integer stat : listOfStats) {
            lSeriesData.add(new XYChart.Data<>(i, stat));
            lSeriesData2.add(new XYChart.Data<>(i, 10 - stat));
            i++;
        }
        seriesME.getData().addAll(lSeriesData);
        seriesME2.getData().addAll(lSeriesData2);
        standingChart.getData().add(seriesME);
        standingChart.getData().add(seriesME2);
    }

    private void startChallenge() {
        Challenge challenge = new Challenge(enemyNameLabel.getText(), yourNameLabel.getText(), queueChoice.getValue().toString(), laneChoice.getValue().toString());
        challenge.setTimeStarted(System.currentTimeMillis());
        System.out.println("challenge = " + challenge);
        runningChallenges.put(enemyNameLabel.getText(), challenge);
        showUser(enemyNameLabel.getText());
//        showActiveChallenge(true);
    }

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
        if (!runningChallenges.containsKey(userName)) {
            showActiveChallenge(false);
            return;
        }
        Challenge activeChallenge = runningChallenges.get(userName);
        try {
            refreshGames(activeChallenge);
        } catch (RiotApiException e) {
            e.printStackTrace();
        }
        addSeriesToChart(activeChallenge.getResultPoints());
        showActiveChallenge(true);
    }

    public void refreshGames(Challenge challenge) throws RiotApiException {
        MatchStatsApiService api = new MatchStatsApiService();
        MatchList matchListByUser = api.getMatchListByUser(challenge.getChallengerName());
        List<MatchReference> collect = matchListByUser.getMatches().stream()
                .filter(matchReference -> matchReference.getLane().equals(challenge.getPosition()))
                .filter(matchReference -> matchReference.getQueue() == 420) //soloq
                .filter(matchReference -> matchReference.getTimestamp() > challenge.getTimeStarted())
                .sorted(Comparator.comparing(MatchReference::getTimestamp).reversed())
                .collect(Collectors.toList());
        int i = 0;
        for (MatchReference matchReference : collect) {
            challenge.addChallengerGame(api.getGameStatsAndTimelineByUser(matchReference.getGameId(), challenge.getChallengerName()));
            i++;
            if (i == 10) {
                break;
            }
        }
        MatchList matchListByUser2 = api.getMatchListByUser(challenge.getOpponentName());
        List<MatchReference> collect2 = matchListByUser2.getMatches().stream()
                .filter(matchReference -> matchReference.getLane().equals(challenge.getPosition()))
                .filter(matchReference -> matchReference.getQueue() == 420) //soloq can be filtered in api, beginn time
                .filter(matchReference -> matchReference.getTimestamp() > challenge.getTimeStarted())
                .sorted(Comparator.comparing(MatchReference::getTimestamp).reversed())
                .collect(Collectors.toList());
        i = 0;
        for (MatchReference matchReference : collect2) {
            challenge.addOpponentsGame(api.getGameStatsAndTimelineByUser(matchReference.getGameId(), challenge.getOpponentName()));
            i++;
            if (i == 10) {
                break;
            }
        }
    }

    @FXML
    public void initialize() {
        runningChallenges = new HashMap<>();
        laneChoice.getItems().addAll(LANE.values());
        queueChoice.getItems().addAll(LeagueQueue.values());
        yourNameLabel.setText("BAD embrandt");
        enemyNameLabel.setText("BAD moerk");
        buttME.setOnAction(event -> {
            startChallenge();
//            try {
//                ParticipantStatsTimeline statsTimeline = new MatchStatsApiService().getGameStatsAndTimelineByUser(3895557833L, "BAD embrandt");
//                System.out.println("statsTimeline.getKDARatio() = " + statsTimeline.getKDARatio());
//                System.out.println("statsTimeline.getDamagePerDeath() = " + statsTimeline.getDamagePerDeath());
//                System.out.println("statsTimeline.getVisionScorePerHour() = " + statsTimeline.getVisionScorePerHour());
//            } catch (RiotApiException e) {
//                e.printStackTrace();
//            }
//            startingChallenge.setVisible(false);
//            activeChallenge.setVisible(true);
        });
//        List<ParticipantStatsTimeline> lStats = new ArrayList<>();
//        List<ParticipantStatsTimeline> lStats2 = new ArrayList<>();
//        try {
//
//            lStats.add(new MatchStatsApiService().getGameStatsAndTimelineByUser(3895557833L, "BAD embrandt"));
//            lStats2.add(new MatchStatsApiService().getGameStatsAndTimelineByUser(3894083936L, "Augustprince"));
//        } catch (RiotApiException e) {
//            e.printStackTrace();
//        }
//        addSeriesToChart("ME", lStats, ParticipantStatsTimeline::getKDARatio);
//        addSeriesToChart("Augustprince", lStats2, ParticipantStatsTimeline::getKDARatio);
    }

    private enum LANE {
        MIDDLE,
        BOTTOM,
        JUNGLE,
        TOP
    }
}
