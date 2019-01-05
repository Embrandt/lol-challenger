package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallenge.RiotApiChallengeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RiotApiChallengeApplication.class})
@TestPropertySource(properties = {
        "rift.explorer.port=8080", "rift.explorer.key=cmlvdDpmY1BfbmxRSW9MQWc2eXB2Q0tzUWRB", "api.base.url=https://127.0.0.1:"
})
public class ClubApiServiceTest {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ClubApiService clubApiService;

    @Test(expected = ResourceAccessException.class)
    public void testGetClubs() {
        clubApiService.getClubs();
    }
}