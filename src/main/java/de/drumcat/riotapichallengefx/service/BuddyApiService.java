package de.drumcat.riotapichallengefx.service;

import de.drumcat.riotapichallengefx.domain.ParticipantStatsTimeline;
import net.rithms.riot.api.ApiConfig;
import net.rithms.riot.api.RiotApi;
import net.rithms.riot.api.RiotApiException;
import net.rithms.riot.api.endpoints.match.dto.*;
import net.rithms.riot.api.endpoints.summoner.dto.Summoner;
import net.rithms.riot.constant.Platform;
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
public class BuddyApiService {

    static final String KEY = loadProperties("application.properties").get("rift.explorer.key").toString();

    static final String PORT = loadProperties("application.properties").get("rift.explorer.port").toString();

    static final String BASE_URL = loadProperties("application.properties").get("api.base.url").toString();

    static final String WEB_API_KEY = loadProperties("application.properties").get("web.api.key").toString();

    static final RiotApi RIOT_API_JAVA = new RiotApi(new ApiConfig().setKey(WEB_API_KEY));

    static final Platform PLATFORM = Platform.EUW;



    public List<String> getBuddies() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<String>> response = restTemplate.exchange(BASE_URL + PORT +"/lol-game-client-chat/v1/buddies", HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), new ParameterizedTypeReference<List<String>>(){
        });

        return response.getBody();
    }

    public Summoner getSummonerByName(String user) throws RiotApiException {
        Summoner summoner = RIOT_API_JAVA.getSummonerByName(PLATFORM, user);

        return summoner;
    }
    private HttpHeaders createHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization",
                "Basic " + KEY);

        return headers;
    }
}