package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static de.drumcat.riotapichallengefx.utils.PropertiesLoader.loadProperties;

@Service
public class MatchStatsApiService {

    private static final String WEB_API_KEY = loadProperties("application.properties").get("web.api.key").toString();

    private static final RiotApi RIOT_API_JAVA = new RiotApi(new ApiConfig().setKey(WEB_API_KEY));

    private static final Platform PLATFORM = Platform.EUW;


    public MatchList getMatchListByUser(String user) throws RiotApiException {
        BuddyApiService buddyApiService = new BuddyApiService();
        Summoner summonerByName = buddyApiService.getSummonerByName(user);

        return RIOT_API_JAVA.getMatchListByAccountId(PLATFORM, summonerByName.getAccountId());
    }

    /**
     * Gets a list of matches played by given user
     *
     * @param user      name of the user
     * @param beginTime earliest time to get games for
     * @param queue     the queue in which the games were played
     * @return a list of match reference data
     * @throws RiotApiException is thrown if no data could be retrieved
     */
    public MatchList getMatchListByUser(String user, long beginTime, int queue) throws RiotApiException {
        BuddyApiService buddyApiService = new BuddyApiService();
        Summoner summonerByName = buddyApiService.getSummonerByName(user);

        return RIOT_API_JAVA.getMatchListByAccountId(PLATFORM, summonerByName.getAccountId(), null,
                Collections.singleton(queue), Collections.singleton(11),
                beginTime, -1L, -1, -1);
    }
    public Match getMatchByMatchId(long gameId) throws RiotApiException {

        return RIOT_API_JAVA.getMatch(PLATFORM, gameId);
    }

    public ParticipantStatsTimeline getGameStatsAndTimelineByUser(Match match, String user) {
        ParticipantStatsTimeline participantStatsTimeline = new ParticipantStatsTimeline();

        long gameTime = match.getGameDuration();
        participantStatsTimeline.setTimePlayed(gameTime);
        Participant participantBySummonerName = match.getParticipantBySummonerName(user);
        ParticipantStats stats = participantBySummonerName.getStats();
        ParticipantTimeline timeline = participantBySummonerName.getTimeline();

        participantStatsTimeline.setStats(stats);
        participantStatsTimeline.setTimeline(timeline);


        return participantStatsTimeline;
    }
}