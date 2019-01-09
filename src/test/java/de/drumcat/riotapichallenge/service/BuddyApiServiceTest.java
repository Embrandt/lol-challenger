package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallengefx.service.BuddyApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.ResourceAccessException;

@RunWith(JUnit4.class)
public class BuddyApiServiceTest {

    @Test(expected = ResourceAccessException.class)
    public void testGetBuddies() {
        BuddyApiService buddyApiService = new BuddyApiService();
        buddyApiService.getBuddies();
    }

    @Test(expected = ResourceAccessException.class)
    public void testGetSummonerByName() {
        BuddyApiService buddyApiService = new BuddyApiService();
        buddyApiService.getSummonerByName("aischnei");
    }
}