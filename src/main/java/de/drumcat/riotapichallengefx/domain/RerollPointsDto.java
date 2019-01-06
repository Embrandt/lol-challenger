package de.drumcat.riotapichallengefx.domain;

public class RerollPointsDto {
    private float currentPoints;
    private float maxRolls;
    private float numberOfRolls;
    private float pointsCostToRoll;
    private float pointsToReroll;


    // Getter Methods

    public float getCurrentPoints() {
        return currentPoints;
    }

    public float getMaxRolls() {
        return maxRolls;
    }

    public float getNumberOfRolls() {
        return numberOfRolls;
    }

    public float getPointsCostToRoll() {
        return pointsCostToRoll;
    }

    public float getPointsToReroll() {
        return pointsToReroll;
    }

    // Setter Methods

    public void setCurrentPoints(float currentPoints) {
        this.currentPoints = currentPoints;
    }

    public void setMaxRolls(float maxRolls) {
        this.maxRolls = maxRolls;
    }

    public void setNumberOfRolls(float numberOfRolls) {
        this.numberOfRolls = numberOfRolls;
    }

    public void setPointsCostToRoll(float pointsCostToRoll) {
        this.pointsCostToRoll = pointsCostToRoll;
    }

    public void setPointsToReroll(float pointsToReroll) {
        this.pointsToReroll = pointsToReroll;
    }
}
