package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.service.MatchStatsApiService;
import net.rithms.riot.api.RiotApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Base64;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void testBase64Encoder(){
        String base64String = Base64.getEncoder().encodeToString("riot:iBJ3e_vvj-0PR4NLMWTNlQ".getBytes());

        assertEquals(base64String, "cmlvdDppQkozZV92dmotMFBSNE5MTVdUTmxR");

    }


}