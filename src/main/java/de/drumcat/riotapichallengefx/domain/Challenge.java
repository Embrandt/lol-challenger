package de.drumcat.riotapichallengefx.domain;

import java.util.ArrayList;
import java.util.List;

public class Challenge {
    private String opponentName;
    private String challengerName;
    private List<ParticipantStatsTimeline> challengerGames;
    private List<ParticipantStatsTimeline> opponentGames;
    private String queue;
    private String position;
    private long timeStarted;

    public Challenge(String opponentName, String challengerName, String queue, String position) {
        this.opponentName = opponentName;
        this.challengerName = challengerName;
        this.queue = queue;
        this.position = position;
        challengerGames = new ArrayList<>();
        opponentGames = new ArrayList<>();
    }

    /**
     * Adds a game to the list for the challenger
     *
     * @param challengersGame new game of the challenger to add
     */
    public void addChallengerGame(ParticipantStatsTimeline challengersGame) {
        challengerGames.add(challengersGame);
    }

    /**
     * Adds a game to the list for the opponent
     *
     * @param opponentsGame new game of the challenger to add
     */
    public void addOpponentsGame(ParticipantStatsTimeline opponentsGame) {
        opponentGames.add(opponentsGame);
    }

    public String getQueue() {
        return queue;
    }

    public String getPosition() {
        return position;
    }

    public List<Integer> getResultPoints() {
        List<Integer> results = new ArrayList<>();
        double kdaChallenger;
        double kdaOpponent;
        long damagePerDeathChallenger;
        long damagePerDeathOpponent;
        double damagePerGoldChallenger;
        double damagePerGoldOpponent;
        double csPerMinuteChallenger;
        double csPerMinuteOpponent;
        double earlyCSDiffChallenger;
        double earlyCSDiffOpponent;
        double earlyGoldDiffChallenger;
        double earlyGoldDiffOpponent;
        double visionScorePerHourChallenger;
        double visionScorePerHourOpponent;
        for (int i = 0; i < challengerGames.size(); i++) {
            int points = 0;
            ParticipantStatsTimeline ChallengerGame = challengerGames.get(i);
            ParticipantStatsTimeline opponentGame = opponentGames.get(i);
            kdaChallenger = +ChallengerGame.getKDARatio();
            kdaOpponent = +opponentGame.getKDARatio();
            if (kdaChallenger > kdaOpponent) {
                points++;
            }
            damagePerDeathChallenger = +ChallengerGame.getDamagePerDeath();
            damagePerDeathOpponent = +opponentGame.getDamagePerDeath();
            if (damagePerDeathChallenger > damagePerDeathOpponent) {
                points++;
            }
            damagePerGoldChallenger = +ChallengerGame.getDamagePerGold();
            damagePerGoldOpponent = +opponentGame.getDamagePerGold();
            if (damagePerGoldChallenger > damagePerGoldOpponent) {
                points++;
            }
            csPerMinuteChallenger = +ChallengerGame.getCSPerMinute();
            csPerMinuteOpponent = +opponentGame.getCSPerMinute();
            if (csPerMinuteChallenger > csPerMinuteOpponent) {
                points++;
            }
            earlyCSDiffChallenger = +ChallengerGame.getEarlyCSAdvantage();
            earlyCSDiffOpponent = +opponentGame.getEarlyCSAdvantage();
            if (earlyCSDiffChallenger > earlyCSDiffOpponent) {
                points++;
            }
            earlyGoldDiffChallenger = +ChallengerGame.getEarlyGoldAdvantage();
            earlyGoldDiffOpponent = +opponentGame.getEarlyGoldAdvantage();
            if (earlyGoldDiffChallenger > earlyGoldDiffOpponent) {
                points++;
            }
            visionScorePerHourChallenger = +ChallengerGame.getVisionScorePerHour();
            visionScorePerHourOpponent = +opponentGame.getVisionScorePerHour();
            if (visionScorePerHourChallenger > visionScorePerHourOpponent) {
                points++;
            }
            results.add(points);

        }
        return results;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public String getChallengerName() {
        return challengerName;
    }

    public long getTimeStarted() {
        return timeStarted;
    }

    public void setTimeStarted(long timeStarted) {
        this.timeStarted = timeStarted;
    }
}
