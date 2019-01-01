package de.drumcat.riotapichallenge.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatApiService {

    public String getLastChatMessage() {

        RestTemplate restTemplate = new RestTemplate();

        //Authorization: Basic cmlvdDpDZEh0dUFBcFhSZm00eWNGaTMtYzZ3

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic cmlvdDpDZEh0dUFBcFhSZm00eWNGaTMtYzZ3");

        ResponseEntity<String> response = restTemplate.exchange("https://127.0.0.1:60841/lol-chat/v1/conversations", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        return response.getBody();
    }
}