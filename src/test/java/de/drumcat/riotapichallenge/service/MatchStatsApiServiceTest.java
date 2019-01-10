package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallengefx.service.MatchStatsApiService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.MatchList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MatchStatsApiServiceTest {

    @Test(expected = RiotApiException.class)
    public void testGetMatches() throws RiotApiException {
        MatchStatsApiService matchStatsApiService = new MatchStatsApiService();
        matchStatsApiService.getMatchListByUser("aischnei");
    }

    @Test(expected = RiotApiException.class)
    public void testGetStats() throws RiotApiException {
        MatchStatsApiService matchStatsApiService = new MatchStatsApiService();
       matchStatsApiService.getGameStatsAndTimelineByUser(1L, "aischnei");
    }

    @Test(expected = RiotApiException.class)
    public void testGetMatchById() throws RiotApiException {
        MatchStatsApiService matchStatsApiService = new MatchStatsApiService();
        matchStatsApiService.getMatchByMatchId(1L);
    }


}