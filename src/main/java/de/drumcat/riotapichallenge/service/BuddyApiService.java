package de.drumcat.riotapichallenge.service;

import de.drumcat.riotapichallenge.domain.SummonerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class BuddyApiService {

    @Value("${rift.explorer.key}")
    String key;

    @Value("${rift.explorer.port}")
    String port;

    @Value("${api.base.url}")
    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public String getBuddies() {
        ResponseEntity<String> response = restTemplate.exchange(baseUrl + port +"/lol-game-client-chat/v1/buddies", HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), String.class);

        return response.getBody();
    }

    public SummonerDto getSummonerByName(String user) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(baseUrl + port +"/lol-summoner/v1/summoners")
                .queryParam("name", user);
        String uriString = builder.build().encode().toUriString();

        ResponseEntity<SummonerDto> response = restTemplate.exchange(uriString, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), SummonerDto.class);

        return response.getBody();
    }

    private HttpHeaders createHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic " + key);

        return headers;
    }
}