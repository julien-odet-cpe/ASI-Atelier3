package com.market.marketmicroservice;

import com.market.marketmicroservice.dao.MarketHeroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class MarketRestCrt {
    @Autowired
    MarketService mService;

    @RequestMapping(method = RequestMethod.POST, value = "/market-rest")
    public Market addMarket(@RequestBody Market market) {
        return mService.addMarket(market);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market-rest")
    public Iterable<MarketHeroDto> getAllMarket() throws IOException, InterruptedException {
        return mService.getOpenMarket();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/market-rest/{id}")
    public void deleteMarket(@PathVariable Integer id) {
        mService.deleteMarket(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/market-rest/hero/{id}")
    public void deleteMarketByHeroId(@PathVariable String id) {
        mService.deleteMarketByHeroId(Integer.parseInt(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market-rest/seller/{id}")
    public Iterable<Market> getOpenMarketBySellerId(@PathVariable String id) {
        return mService.getOpenMarketBySellerId(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/market-rest/{id}")
    public void updateMarket(@PathVariable String id) {
        mService.getMarket(Integer.valueOf(id));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/market-rest/buy/{id}")
    public void buyMarket(@RequestBody MarketHeroDto marketHeroDto,
                          @PathVariable String id) throws IOException, InterruptedException {
        mService.buyMarket(marketHeroDto, Integer.valueOf(id));
    }
}
