package de.drumcat.riotapichallenge.controller;

import de.drumcat.riotapichallenge.service.ChatApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RiotApiController {

    @Autowired
    ChatApiService chatApiService;

    @RequestMapping(method = RequestMethod.GET, path = "messages", produces = "text/plain")
    public String getLastChatMessage() {
        return chatApiService.getLastChatMessage();
    }

}
