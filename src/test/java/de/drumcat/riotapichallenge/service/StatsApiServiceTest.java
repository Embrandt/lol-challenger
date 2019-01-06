package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallengefx.RiotApiChallengeFxApplication;
import de.drumcat.riotapichallengefx.service.StatsApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RiotApiChallengeFxApplication.class})
@TestPropertySource(locations = {
        "classpath:application.properties"})

public class StatsApiServiceTest {

    @Autowired
    StatsApiService statsApiService;

    @Test(expected = ResourceAccessException.class)
    public void testGetStats() {
        statsApiService.getUserStatsByPuuid("aeae2");
    }
}