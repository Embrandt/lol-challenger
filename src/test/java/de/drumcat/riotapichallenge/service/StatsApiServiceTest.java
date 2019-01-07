package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallengefx.service.StatsApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.web.client.ResourceAccessException;

@RunWith(JUnit4.class)
public class StatsApiServiceTest {

    @Test(expected = ResourceAccessException.class)
    public void testGetStats() {
        StatsApiService statsApiService = new StatsApiService();
        statsApiService.getUserStatsByPuuid("aeae2");
    }
}