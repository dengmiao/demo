package com.corgi.mongodb.repository;

import com.corgi.mongodb.entity.Body;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: BodyRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-06-10 09:23
 **/
@Repository
public interface BodyRepository extends ReactiveMongoRepository<Body, String> {
}
