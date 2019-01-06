package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.UserStatsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StatsApiService {

    @Value("${rift.explorer.key}")
    String key;

    @Value("${rift.explorer.port}")
    String port;

    @Value("${api.base.url}")
    String baseUrl;

    @Autowired
    RestTemplate restTemplate;

    public List<UserStatsDto> getUserStatsByPuuid(String puuid) {
        ResponseEntity<List<UserStatsDto>> response = restTemplate.exchange(baseUrl + port + "/lol-career-stats/v1/summoner-games/"+ puuid, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<UserStatsDto>>(){
        });

        return response.getBody();
    }

    private HttpHeaders createHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic " + key);

        return headers;
    }
}