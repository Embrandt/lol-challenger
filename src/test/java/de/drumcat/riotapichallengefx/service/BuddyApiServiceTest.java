package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.service.BuddyApiService;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class BuddyApiServiceTest {

    @Test(expected = ResourceAccessException.class)
    public void testGetBuddies() {
        BuddyApiService buddyApiService = new BuddyApiService();
        buddyApiService.getBuddies();
    }

    @Test
    public void testGetSummonerByName() throws RiotApiException {
        BuddyApiService buddyApiService = new BuddyApiService();
        Summoner summoner = buddyApiService.getSummonerByName("aischnei");

        assertNotNull(summoner);


    }
}