package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
import org.springframework.stereotype.Service;

import static de.drumcat.riotapichallengefx.utils.PropertiesLoader.loadProperties;

@Service
public class MatchStatsApiService {

    static final String WEB_API_KEY = loadProperties("application.properties").get("web.api.key").toString();

    static final RiotApi RIOT_API_JAVA = new RiotApi(new ApiConfig().setKey(WEB_API_KEY));

    static final Platform PLATFORM = Platform.EUW;


    public MatchList getMatchListByUser(String user) throws RiotApiException {
        BuddyApiService buddyApiService = new BuddyApiService();
        Summoner summonerByName = buddyApiService.getSummonerByName(user);
        MatchList matchList = RIOT_API_JAVA.getMatchListByAccountId(PLATFORM, summonerByName.getAccountId());

        return matchList;
    }

    public Match getMatchByMatchId(long gameId) throws RiotApiException {
        Match match = RIOT_API_JAVA.getMatch(PLATFORM, gameId);

        return match;
    }

    public ParticipantStatsTimeline getGameStatsAndTimelineByUser(long gameId, String user) throws RiotApiException {
        ParticipantStatsTimeline participantStatsTimeline = new ParticipantStatsTimeline();

        Match matchByMatchId = getMatchByMatchId(gameId);
        Participant participantBySummonerName = matchByMatchId.getParticipantBySummonerName(user);
        ParticipantStats stats = participantBySummonerName.getStats();
        ParticipantTimeline timeline = participantBySummonerName.getTimeline();

        participantStatsTimeline.setStats(stats);
        participantStatsTimeline.setTimeline(timeline);


        return participantStatsTimeline;
    }
}