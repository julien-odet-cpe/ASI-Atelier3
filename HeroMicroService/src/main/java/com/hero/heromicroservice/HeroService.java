package com.hero.heromicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroService {
    @Autowired
    HeroRepository hRepository;

    public Hero addHero(Hero heroCreateDto) {
        Hero h = new Hero(
                heroCreateDto.getName(),
                heroCreateDto.getDescription(),
                heroCreateDto.getImgUrl(),
                heroCreateDto.getHp(),
                heroCreateDto.getAttack(),
                heroCreateDto.getDefense(),
                heroCreateDto.getEnergy(),
                heroCreateDto.getPlayerId()
        );
        return hRepository.save(h);
    }

    public Hero getHero(int id) {
        Optional<Hero> hOpt = hRepository.findById(id);
        return hOpt.orElse(null);
    }

    public Hero updateHero(Hero h) {
        return hRepository.save(h);
    }

    public List<Hero> findByPlayerId(Integer playerId) {
        return hRepository.findByPlayerId(playerId);
    }
}
