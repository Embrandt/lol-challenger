package de.drumcat.riotapichallenge.controller;

import de.drumcat.riotapichallenge.service.ClubApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RiotApiController {

    @Autowired
    ClubApiService clubApiService;

    @RequestMapping(value = "/clubs",
            produces = {"application/json;charset=UTF-8"},
            method = RequestMethod.GET)
    public ResponseEntity<String> getLastChatMessage() {
        String lastChatMessage = clubApiService.getClubs();
        return new ResponseEntity<>(lastChatMessage, HttpStatus.OK);
    }

}
