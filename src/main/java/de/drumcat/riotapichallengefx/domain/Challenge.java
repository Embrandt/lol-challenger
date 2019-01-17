package de.drumcat.riotapichallengefx.domain;

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
    }
}
