package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.UserStatsDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static de.drumcat.riotapichallengefx.utils.PropertiesLoader.loadProperties;

@Service
public class ClientStatsApiService {

    static final String KEY = loadProperties("application.properties").get("rift.explorer.key").toString();

    static final String PORT = loadProperties("application.properties").get("rift.explorer.port").toString();;

    static final String BASE_URL = loadProperties("application.properties").get("api.base.url").toString();;

    public List<UserStatsDto> getUserStatsByPuuid(String puuid) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserStatsDto>> response = restTemplate.exchange(BASE_URL + PORT + "/lol-career-stats/v1/summoner-games/"+ puuid, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<UserStatsDto>>(){
        });

        return response.getBody();
    }

    private HttpHeaders createHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic " + KEY);

        return headers;
    }
}