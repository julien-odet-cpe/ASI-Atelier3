package com.player.playermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerRestCrt {
    @Autowired
    PlayerService pService;

    @RequestMapping(method = RequestMethod.POST, value = "/player-rest")
    public Player addPlayer(@RequestBody Player player) {
        return pService.addPlayer(player);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/player-rest/{id}")
    public Player getPlayer(@PathVariable String id) {
        return pService.getPlayer(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/player-rest/login")
    public Player login(@RequestBody PlayerCredential credential) {
        return pService.login(credential.getUsername(), credential.getPassword());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/player-rest")
    public Player updatePlayer(@RequestBody Player player) {
        return pService.updatePlayer(player);
    }
}

