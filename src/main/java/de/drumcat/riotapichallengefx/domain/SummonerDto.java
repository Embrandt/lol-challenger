package de.drumcat.riotapichallengefx.domain;

import javax.persistence.*;

@Entity
@Table(name = "BUDDIES")
public class SummonerDto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;
    @Column
    private float accountId;
    @Column
    private String displayName;
    @Column
    private String internalName;
    @Column
    private String lastSeasonHighestRank;
    @Column
    private float percentCompleteForNextLevel;
    @Column
    private float profileIconId;
    @Column
    private String puuid;
    @Transient
    private RerollPointsDto rerollPointsDto;
    @Column
    private float summonerId;
    @Column
    private float summonerLevel;
    @Column
    private float xpSinceLastLevel;
    @Column
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
