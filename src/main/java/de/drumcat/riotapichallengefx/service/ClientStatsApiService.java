package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.SummonerDto;
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

    private static final String KEY = System.getProperty("key");

    private static final String PORT = System.getProperty("port");

    private static final String BASE_URL = loadProperties("application.properties").get("api.base.url").toString();

    public List<UserStatsDto> getUserStatsByPuuid(String puuid) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<UserStatsDto>> response = restTemplate.exchange(BASE_URL + PORT + "/lol-career-stats/v1/summoner-games/"+ puuid, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<UserStatsDto>>(){
        });

        return response.getBody();
    }

    /**
     * Sends a simple text message to a given user
     *
     * @param username name of the user to send the message to
     * @param message  message to send
     */
    public void sendMessage(String username, String message) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(BASE_URL + PORT + "/lol-game-client-chat/v1/instant-messages?summonerName=" + username +
                        "&message=" + message, HttpMethod.POST,
                new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<UserStatsDto>>() {
                });
    }
    /**
     * Gets the current logged in user
     *
     * @return the current logged in user
     */
    public SummonerDto getCurrentUser() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SummonerDto> response = restTemplate.exchange(BASE_URL + PORT + "lol-summoner/v1" +
                        "/current-summoner", HttpMethod.GET, new HttpEntity<>(createHttpHeaders()),
                new ParameterizedTypeReference<SummonerDto>() {
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