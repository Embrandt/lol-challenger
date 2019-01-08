package de.drumcat.riotapichallengefx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDto {
    private DiagramStats careerStatsDto;

    @JsonProperty(value = "CareerStats.js")
    public DiagramStats getCareerStats() {
        return careerStatsDto;
    }

    public void setCareerStats(DiagramStats careerStatsDto) {
        this.careerStatsDto = careerStatsDto;
    }
}
