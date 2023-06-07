package com.player.playermicroservice;

import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Integer> {

    public Player findById(int id);

    public Player findByUsernameAndPassword(String username, String password);
}