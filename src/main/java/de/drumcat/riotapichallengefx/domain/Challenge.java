package de.drumcat.riotapichallengefx.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CHALLENGE")
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private String opponentName;
    @Column
    private String challengerName;
    @Transient
    private List<ParticipantStatsTimeline> challengerGames;
    @Transient
    private List<ParticipantStatsTimeline> opponentGames;
    @Column
    private int queue;
    @Column
    private String position;
    @Column
    private String role;
    @Column
    private long timeStarted;

    public Challenge() {
        challengerGames = new ArrayList<>();
        opponentGames = new ArrayList<>();
    }

    public Challenge(String opponentName, String challengerName, int queue, String position) {
        this.opponentName = opponentName;
        this.challengerName = challengerName;
        this.queue = queue;
        this.position = position;
        challengerGames = new ArrayList<>();
        opponentGames = new ArrayList<>();
    }

    public void setPosition(String position) {
        this.position = position;
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

    public int getQueue() {
        return queue;
    }

    public String getPosition() {
        return position;
    }

    /**
     * Calculates the result of the challenge over the course of all games
     * There are 10 categorizes to score in and for each category the challenger
     * has a higher value then the opponent, the challenger is awarded one point
     *
     * @return list with the points for the challenger over time
     */
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
        int challeneProgress = Math.min(challengerGames.size(), opponentGames.size());
        for (int i = 0; i < challeneProgress; i++) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
