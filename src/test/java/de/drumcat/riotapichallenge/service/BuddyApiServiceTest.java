package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallenge.RiotApiChallengeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RiotApiChallengeApplication.class})
@TestPropertySource(locations = {
        "classpath:application.properties"})

public class BuddyApiServiceTest {

    @Autowired
    BuddyApiService buddyApiService;

    @Test(expected = ResourceAccessException.class)
    public void testGetBuddies() {
        buddyApiService.getBuddies();
    }

    @Test(expected = ResourceAccessException.class)
    public void testGetSummonerByName() {
        buddyApiService.getSummonerByName("aischnei");
    }
}