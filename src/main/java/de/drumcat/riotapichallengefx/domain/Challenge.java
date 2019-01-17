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
        challengerGames.add(opponentsGame);
    }

    public String getQueue() {
        return queue;
    }

    public String getPosition() {
        return position;
    }
}
