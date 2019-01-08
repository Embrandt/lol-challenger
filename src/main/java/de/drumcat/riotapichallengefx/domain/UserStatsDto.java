package de.drumcat.riotapichallengefx.domain;

public class UserStatsDto {
    private int championId;
    private long gameId;
    private int gamesCalculated;
    private String lane;
    private String platformId;
    private String puuid;
    private String queueType;
    private String role;
    private int season;
    private StatsDto statsDto;
    private int teamId;
    private long timestamp;


    // Getter Methods

    public float getChampionId() {
        return championId;
    }

    public float getGameId() {
        return gameId;
    }

    public float getGamesCalculated() {
        return gamesCalculated;
    }

    public String getLane() {
        return lane;
    }

    public String getPlatformId() {
        return platformId;
    }

    public String getPuuid() {
        return puuid;
    }

    public String getQueueType() {
        return queueType;
    }

    public String getRole() {
        return role;
    }

    public float getSeason() {
        return season;
    }

    public StatsDto getStats() {
        return statsDto;
    }

    public float getTeamId() {
        return teamId;
    }

    public float getTimestamp() {
        return timestamp;
    }

    // Setter Methods

    public void setChampionId(int championId) {
        this.championId = championId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public void setGamesCalculated(int gamesCalculated) {
        this.gamesCalculated = gamesCalculated;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public void setPuuid(String puuid) {
        this.puuid = puuid;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public void setStats(StatsDto statsDto) {
        this.statsDto = statsDto;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
