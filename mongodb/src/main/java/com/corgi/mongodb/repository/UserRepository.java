package com.corgi.mongodb.repository;

import com.corgi.mongodb.entity.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @title: UserRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:19
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    /**
     * 按年龄查找用户
     * @param start
     * @param end
     * @return
     */
    Flux<User> findByAgeBetween(Integer start, Integer end);

    /**
     * 本地sql 按年龄查找用户
     * @param start
     * @param end
     * @return
     */
    @Query("{'age':{'$gte': ?0, '$lte': ?1}}")
    Flux<User> findByNativeQuery(Integer start, Integer end);
}
