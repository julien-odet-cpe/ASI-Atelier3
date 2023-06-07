package com.hero.heromicroservice;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HeroRepository extends CrudRepository<Hero, Integer> {

    public List<Hero> findByPlayerId(Integer playerId);
}
