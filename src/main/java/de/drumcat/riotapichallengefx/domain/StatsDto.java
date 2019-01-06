package de.drumcat.riotapichallengefx.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatsDto {
    CareerStatsDto careerStatsDto;

    @JsonProperty(value = "CareerStats.js")
    public CareerStatsDto getCareerStats() {
        return careerStatsDto;
    }

    public void setCareerStats(CareerStatsDto careerStatsDto) {
        this.careerStatsDto = careerStatsDto;
    }
}
