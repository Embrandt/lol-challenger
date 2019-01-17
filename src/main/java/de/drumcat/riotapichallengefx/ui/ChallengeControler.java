package de.drumcat.riotapichallengefx.ui;

import de.drumcat.riotapichallengefx.domain.Challenge;
import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import net.rithms.riot.api.endpoints.league.constant.LeagueQueue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChallengeControler {
    public ChoiceBox laneChoice;
    public ChoiceBox queueChoice;
    public Button buttME;
    public Pane activeChallenge;
    public Pane startingChallenge;
    public LineChart standingChart;
    public Label yourNameLabel;
    public Label enemyNameLabel;
    Map<String, Challenge> runningChallenges;

    private void addSeriesToChart(String summoner, List<ParticipantStatsTimeline> listOfStats, Function<ParticipantStatsTimeline, Number> valueFunction) {
        XYChart.Series<String, Number> seriesME = new XYChart.Series<>();
        seriesME.setName(summoner);
        int i = 0;
        List<XYChart.Data<String, Number>> lSeriesData = new ArrayList<>();
        for (ParticipantStatsTimeline stat : listOfStats) {
            String entryNumber = Integer.toString(i);
            Number computedValue = valueFunction.apply(stat);
            lSeriesData.add(new XYChart.Data<>(entryNumber, computedValue));
            i++;
        }
        seriesME.getData().addAll(lSeriesData);
        standingChart.getData().add(seriesME);
    }

    private void startChallenge() {
        Challenge challenge = new Challenge(enemyNameLabel.getText(), yourNameLabel.getText(), queueChoice.getValue().toString(), laneChoice.getValue().toString());
        System.out.println("challenge = " + challenge);
        runningChallenges.put(enemyNameLabel.getText(), challenge);
        showActiveChallenge(true);
    }

    private void showActiveChallenge(boolean isChallengeActive) {
        startingChallenge.setVisible(!isChallengeActive);
        activeChallenge.setVisible(isChallengeActive);
    }

    public void showUser(String userName) {
        //TODO search for active challenge and set right window visible
        showActiveChallenge(false);
    }

    @FXML
    public void initialize() {
        runningChallenges = new HashMap<>();
        laneChoice.getItems().addAll(LANE.values());
        queueChoice.getItems().addAll(LeagueQueue.values());
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
