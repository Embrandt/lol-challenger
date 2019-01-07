package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.SummonerDto;
import de.drumcat.riotapichallengefx.domain.UserStatsDto;
import de.drumcat.riotapichallengefx.utils.PropertiesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static de.drumcat.riotapichallengefx.utils.PropertiesLoader.loadProperties;

@Service
public class BuddyApiService {

    static final String KEY = loadProperties("application.properties").get("rift.explorer.key").toString();

    static final String PORT = loadProperties("application.properties").get("rift.explorer.port").toString();

    static final String BASE_URL = loadProperties("application.properties").get("api.base.url").toString();


    public List<String> getBuddies() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> response = restTemplate.exchange(BASE_URL + PORT +"/lol-game-client-chat/v1/buddies", HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<String>>(){
        });

        return response.getBody();
    }

    public SummonerDto getSummonerByName(String user) {
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(BASE_URL + PORT +"/lol-summoner/v1/summoners")
                .queryParam("name", user);
        String uriString = builder.build().encode().toUriString();

        ResponseEntity<SummonerDto> response = restTemplate.exchange(uriString, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), SummonerDto.class);

        return response.getBody();
    }

    private HttpHeaders createHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic " + KEY);

        return headers;
    }
}