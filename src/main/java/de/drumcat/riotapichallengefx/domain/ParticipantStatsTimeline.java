package de.drumcat.riotapichallengefx.domain;

import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
import net.rithms.riot.api.endpoints.match.dto.ParticipantTimeline;

public class ParticipantStatsTimeline {

    private ParticipantStats stats;
    private ParticipantTimeline timeline;

    public ParticipantStats getStats() {
        return stats;
    }

    public void setStats(ParticipantStats stats) {
        this.stats = stats;
    }

    public ParticipantTimeline getTimeline() {
        return timeline;
    }

    public void setTimeline(ParticipantTimeline timeline) {
        this.timeline = timeline;
    }
}