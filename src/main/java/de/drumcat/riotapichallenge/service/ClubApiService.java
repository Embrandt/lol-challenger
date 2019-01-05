package de.drumcat.riotapichallenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClubApiService {

    @Value("${rift.explorer.key}")
    String key;

    @Value("${rift.explorer.port}")
    String port;

    @Value("${api.base.url}")
    String baseUrl;

    public String getClubs() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        //example Authorization: Basic cmlvdDpDZEh0dUFBcFhSZm00eWNGaTMtYzZ3
        headers.set("Authorization",
                "Basic " + key);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + port +"/lol-clubs/v1/clubs", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        return response.getBody();
    }
}