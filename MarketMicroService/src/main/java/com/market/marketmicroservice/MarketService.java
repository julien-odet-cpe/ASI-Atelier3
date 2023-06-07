package com.market.marketmicroservice;

import com.market.marketmicroservice.dao.HeroDao;
import com.market.marketmicroservice.dao.MarketHeroDto;
import com.market.marketmicroservice.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MarketService {
    @Autowired
    MarketRepository mRepository;

    HttpClient client = HttpClient.newHttpClient();

    public Market addMarket(Market m) {
        Market newMarket = new Market(m.getHeroId(), m.getSellerId(), m.getPrice());
        return mRepository.save(newMarket);
    }

    public Market getMarket(Integer id) {
        return mRepository.findById(id).orElse(null);
    }

    public Market buyMarket(MarketHeroDto marketHeroDto, Integer buyerId) throws IOException, InterruptedException {
        assert marketHeroDto.market != null;
        marketHeroDto.market.setBuyerId(buyerId);
        marketHeroDto.market.setSoldDate(new Date());
        mRepository.save(marketHeroDto.market);
        PlayerDao seller = this.getPlayer(marketHeroDto.market.getSellerId());
        PlayerDao buyer = this.getPlayer(buyerId);
        HeroDao hero = this.getHero(marketHeroDto.market.getHeroId());
        seller.setMoney(seller.getMoney() + marketHeroDto.market.getPrice());
        buyer.setMoney(buyer.getMoney() - marketHeroDto.market.getPrice());
        hero.setPlayerId(buyerId);
        this.updateHero(hero);
        this.updatePlayer(seller);
        this.updatePlayer(buyer);

        return marketHeroDto.market;
    }

    private PlayerDao getPlayer(Integer id) throws IOException, InterruptedException {
        return (PlayerDao) client.send(HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/player-rest/" + id))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    private HeroDao getHero(Integer id) throws IOException, InterruptedException {
        return (HeroDao) client.send(HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/hero-rest/" + id))
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    private void updatePlayer(PlayerDao player) throws IOException, InterruptedException {
        client.send(HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8081/player-rest/" + player.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(player.toString()))
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    private void updateHero(HeroDao hero) throws IOException, InterruptedException {
        client.send(HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/hero-rest/" + hero.getId()))
                .PUT(HttpRequest.BodyPublishers.ofString(hero.toString()))
                .build(), HttpResponse.BodyHandlers.ofString());
    }

    public Iterable<Market> getAllMarket() {
        return mRepository.findAll();
    }

    public void deleteMarket(Integer id) {
        mRepository.deleteById(id);
    }

    public void deleteMarketByHeroId(Integer heroId) {
        Market m = mRepository.findByHeroId(heroId);
        mRepository.deleteById(m.getId());
    }

    public Iterable<MarketHeroDto> getOpenMarket() throws IOException, InterruptedException {
        List<MarketHeroDto> marketHeroDtos = new LinkedList<MarketHeroDto>();

        for (Market m : mRepository.findAll()) {
            if (m.getBuyerId() == null) {
                MarketHeroDto marketHeroDto = new MarketHeroDto();
                marketHeroDto.hero = this.getHero(m.getHeroId());
                marketHeroDto.market = m;
                marketHeroDtos.add(marketHeroDto);
            }
        }
        return marketHeroDtos;
    }

    public Iterable<Market> getOpenMarketBySellerId(Integer sellerId) {
        List<Market> markets = new LinkedList<Market>();
        Iterable<Market> mA = mRepository.findBySellerId(sellerId);
        for (Market m : mA) {
            if (m.getBuyerId() == null) {
                markets.add(m);
            }
        }
        return markets;
    }
}