package com.hero.heromicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HeroRestCrt {
    @Autowired
    HeroService hService;

    @RequestMapping(method = RequestMethod.POST, value = "/hero-rest")
    public Hero addHero(@RequestBody Hero heroCreateDto) {
        return hService.addHero(heroCreateDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hero-rest/player/{playerId}")
    public Iterable<Hero> getHeroesByPlayerId(@PathVariable String playerId) {
        return hService.findByPlayerId(Integer.valueOf(playerId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hero-rest/{id}")
    public Hero getHero(@PathVariable String id) {
        Hero h = hService.getHero(Integer.valueOf(id));
        return h;
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/hero-rest")
    public Hero updateHero(@RequestBody Hero hero) {
        return hService.updateHero(hero);
    }
}
