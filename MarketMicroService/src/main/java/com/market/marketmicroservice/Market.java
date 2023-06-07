package com.market.marketmicroservice;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Market {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer heroId;

    private Integer sellerId;

    private Integer buyerId;

    private float price;

    private Date creationDate;

    private Date soldDate;

    public Market() {
    }

    public Market(Integer heroId, Integer sellerId, float price) {
        this.heroId = heroId;
        this.sellerId = sellerId;
        this.price = price;
        this.creationDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeroId() {
        return heroId;
    }

    public void setHeroId(Integer heroId) {
        this.heroId = heroId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }
}