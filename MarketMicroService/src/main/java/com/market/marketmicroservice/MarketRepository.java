package com.market.marketmicroservice;

import org.springframework.data.repository.CrudRepository;

public interface MarketRepository extends CrudRepository<Market, Integer> {

    public Market findByHeroId(Integer heroId);

    public Iterable<Market> findBySellerId(Integer sellerId);
}
