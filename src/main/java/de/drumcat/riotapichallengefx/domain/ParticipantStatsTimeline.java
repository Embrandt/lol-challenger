package de.drumcat.riotapichallengefx.domain;

import net.rithms.riot.api.endpoints.match.dto.ParticipantStats;
import net.rithms.riot.api.endpoints.match.dto.ParticipantTimeline;

public class ParticipantStatsTimeline {

    private ParticipantStats stats;
    private ParticipantTimeline timeline;
    // game time in seconds
    private long timePlayed;

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

    /**
     * Gets the KDA ratio: the ratio between kills, assists and deaths per game
     *
     * @return the KDA ratio
     */
    public double getKDARatio() {
        return (double) (stats.getKills() + stats.getAssists()) / stats.getDeaths();
    }

    public float getKillParticipation() {
        return -1;
        //TODO calculate TEAM kills return (stats.getKills() + stats.getAssists()) / stats.getTeamKills() * 100;
    }

    /**
     * Gets the total damage dealt to champions per death
     * rounded down to the next long
     *
     * @return total damage dealt to champions per death
     */
    public long getDamagePerDeath() {
        return stats.getTotalDamageDealtToChampions() / stats.getDeaths();
    }

    /**
     * Gets the damage done to champions on average per death
     *
     * @return damage done on average per death
     */
    public double getDamagePerGold() {
        return (double) stats.getTotalDamageDealtToChampions() / stats.getGoldEarned();
    }

    /**
     * Gets how many minions and monsters were killed per minute on average
     *
     * @return amount of minions and monsters killed per minute
     */
    public double getCSPerMinute() {
        return (double) (stats.getTotalMinionsKilled() - stats.getNeutralMinionsKilled()) / timePlayed * 60;
    }

    /**
     * Gets the damage done on in percent of team damage
     *
     * @return damage done compared to the team
     */
    public double getDamageShare() {
        return -1;
//       TODO  return stats.getTotalDamageDealt() / getTeamDamage() * 100;
    }

    /**
     * Gets the vision score average per hour
     *
     * @return the vision score average per hour
     */
    public double getVisionScorePerHour() {
        return (double) stats.getVisionScore() / timePlayed * 3600;
    }

    /**
     * Sets the time played in seconds
     *
     * @param timePlayed time played in seconds
     */
    public void setTimePlayed(long timePlayed) {
        this.timePlayed = timePlayed;
    }

    /**
     * Gets the average advantage of creep score per minute
     * in the first 10 minutes of the game
     *
     * @return early cs advantage per minute
     */
    public double getEarlyCSAdvantage() {
        return timeline.getCsDiffPerMinDeltas().get("0-10");
    }

    /**
     * Gets the average advantage of gold per minute
     * in the first 10 minutes of the game
     *
     * @return early gold advantage per minute
     */
    public double getEarlyGoldAdvantage() {
        return timeline.getGoldPerMinDeltas().get("0-10");
    }

    /**
     * Gets the percent of participation in taking down objectives
     * Returns 0 if the team took no objectives
     *
     * @return the percent of participation in taking down objectives
     */
    public double getObjectiveControlRatio() {
        return -1;
    }
}