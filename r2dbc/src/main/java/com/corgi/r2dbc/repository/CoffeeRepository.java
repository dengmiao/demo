package com.corgi.r2dbc.repository;

import com.corgi.r2dbc.model.Coffee;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

/**
 * @title: CoffeeRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-05-28 16:03
 **/
public interface CoffeeRepository extends ReactiveCrudRepository<CoffeeRepository, Long> {

    @Query("select * from t_coffee where name = $1")
    Flux<Coffee> findByName(String name);
}
