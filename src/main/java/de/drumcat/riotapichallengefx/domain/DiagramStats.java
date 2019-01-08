package de.drumcat.riotapichallengefx.domain;

/**
 * This class extends the career stats to get the data used for visual representation directly
 * converted to the same values used in the stats tab of the League client
 */
public class DiagramStats extends CareerStatsDto {

    /**
     * Gets the KDA ratio: the ratio between kills, assists and deaths per game
     *
     * @return the KDA ratio
     */
    public float getKDARatio() {
        return (getKills() + getAssists()) / getDeaths();
    }

    /**
     * Gets the percentage of personal participation for team kills
     *
     * @return personal participation for team kills
     */
    public float getKillParticipation() {
        return (getKills() + getAssists()) / getTeamKills() * 100;
    }

    /**
     * Gets the damage done on average per death
     * rounded up to the next int
     *
     * @return damage done on average per death
     */
    public int getDamagePerDeath() {
        return (int) Math.ceil(getDamage() / getDeaths());
    }

    /**
     * Gets the damage done on in percent of
     * team damage
     *
     * @return damage done compared to the team
     */
    public double getDamageShare() {
        return getDamage() / getTeamDamage() * 100;
    }

    /**
     * Gets the amount of damage done per gold earned
     *
     * @return the amount of damage done per gold earned
     */
    public double getDamagePerGold() {
        return getDamage() / getGoldEarned();
    }

    /**
     * Gets how many minions and monsters were killed per minute as average
     *
     * @return amount of minions and monsters killed per minute
     */
    public double getCSPerMinute() {
        return getCsScore() / getTimePlayed() * 60000;
    }

    /**
     * Gets the percent of participation in taking down objectives
     * Returns 0 if the team took no objectives
     *
     * @return the percent of participation in taking down objectives
     */
    public double getObjectiveControlRatio() {
        if (getTeamObjectivesTaken() == 0) {
            System.out.println("no objectives taken");
            return 0;
        }
        return getObjectiveTakenInvolved() / getTeamObjectivesTaken() * 100;
    }

    /**
     * Gets the vision score average per hour
     *
     * @return the vision score average per hour
     */
    public double getVisionScorePerHour() {
        return getVisionScore() / (getTimePlayed() / 3600000);
    }

    /**
     * Gets the percent of kills and assists that were converted to taking an objective
     *
     * @return percent of kills and assists converted to taking an objective
     */
    public double getKillConversionRatio() {
        return getConvertedKillAndAssists() / (getKills() + getAssists()) * 100;
    }
}
