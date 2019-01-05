package de.drumcat.riotapichallenge.domain;

public class SummonerDto {

    private float accountId;
    private String displayName;
    private String internalName;
    private String lastSeasonHighestRank;
    private float percentCompleteForNextLevel;
    private float profileIconId;
    private String puuid;
    RerollPointsDto rerollPointsDto;
    private float summonerId;
    private float summonerLevel;
    private float xpSinceLastLevel;
    private float xpUntilNextLevel;


    // Getter Methods

    public float getAccountId() {
        return accountId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getLastSeasonHighestRank() {
        return lastSeasonHighestRank;
    }

    public float getPercentCompleteForNextLevel() {
        return percentCompleteForNextLevel;
    }

    public float getProfileIconId() {
        return profileIconId;
    }

    public String getPuuid() {
        return puuid;
    }

    public RerollPointsDto getRerollPointsDto() {
        return rerollPointsDto;
    }

    public float getSummonerId() {
        return summonerId;
    }

    public float getSummonerLevel() {
        return summonerLevel;
    }

    public float getXpSinceLastLevel() {
        return xpSinceLastLevel;
    }

    public float getXpUntilNextLevel() {
        return xpUntilNextLevel;
    }

    // Setter Methods

    public void setAccountId(float accountId) {
        this.accountId = accountId;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public void setLastSeasonHighestRank(String lastSeasonHighestRank) {
        this.lastSeasonHighestRank = lastSeasonHighestRank;
    }

    public void setPercentCompleteForNextLevel(float percentCompleteForNextLevel) {
        this.percentCompleteForNextLevel = percentCompleteForNextLevel;
    }

    public void setProfileIconId(float profileIconId) {
        this.profileIconId = profileIconId;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public void setRerollPointsDto(RerollPointsDto rerollPointsDto) {
        this.rerollPointsDto = rerollPointsDto;
    }

    public void setSummonerId(float summonerId) {
        this.summonerId = summonerId;
    }

    public void setSummonerLevel(float summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public void setXpSinceLastLevel(float xpSinceLastLevel) {
        this.xpSinceLastLevel = xpSinceLastLevel;
    }

    public void setXpUntilNextLevel(float xpUntilNextLevel) {
        this.xpUntilNextLevel = xpUntilNextLevel;
    }
}
