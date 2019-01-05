package de.drumcat.riotapichallenge.controller;

import de.drumcat.riotapichallenge.domain.SummonerDto;
import de.drumcat.riotapichallenge.domain.UserStatsDto;
import de.drumcat.riotapichallenge.service.BuddyApiService;
import de.drumcat.riotapichallenge.service.StatsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RiotApiController {

    @Autowired
    BuddyApiService buddyApiService;

    @Autowired
    StatsApiService statsApiService;

    @RequestMapping(value = "/buddies",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    public ResponseEntity<String> getBuddies() {
        String buddies = buddyApiService.getBuddies();
        return new ResponseEntity<>(buddies, HttpStatus.OK);
    }

    @RequestMapping(value = "/summonerByName",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    public ResponseEntity<SummonerDto> getSummonerByName(@RequestParam("name") String user) {
        SummonerDto summoner = buddyApiService.getSummonerByName(user);
        return new ResponseEntity<>(summoner, HttpStatus.OK);
    }

    @RequestMapping(value = "/careerStatsByPuuid",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    public ResponseEntity<List<UserStatsDto>> getUserStatsByPuuid(@RequestParam("puuid") String puuid) {
        List<UserStatsDto> stats = statsApiService.getUserStatsByPuuid(puuid);
        return new ResponseEntity<>(stats, HttpStatus.OK);
    }
}
