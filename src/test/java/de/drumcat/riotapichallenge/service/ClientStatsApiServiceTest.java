package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallengefx.service.ClientStatsApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.ResourceAccessException;

@RunWith(JUnit4.class)
public class ClientStatsApiServiceTest {

    @Test(expected = ResourceAccessException.class)
    public void testGetStats() {
        ClientStatsApiService clientStatsApiService = new ClientStatsApiService();
        clientStatsApiService.getUserStatsByPuuid("aeae2");
    }
}