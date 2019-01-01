package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallenge.RiotApiChallengeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RiotApiChallengeApplication.class })
public class ChatApiServiceTest {

    @Autowired
    RestTemplate restTemplate;

    ChatApiService chatApiService = new ChatApiService();

    @Test(expected = ResourceAccessException.class)
    public void testGetLastChatMessage() {
        String lastChatMessage = chatApiService.getLastChatMessage();
    }
}