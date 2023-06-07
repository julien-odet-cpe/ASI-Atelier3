package com.hero.heromicroservice;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Hero {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    private String description;
    private int hp;

    private int attack;

    private int defense;

    private int energy;
    private String imgUrl;

    private Integer playerId;

    public Hero() {
    }

    public Hero(String name, String description, String imgUrl, int hp, int attack, int defense, int energy, Integer playerId) {
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.energy = energy;
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", energy=" + energy +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}