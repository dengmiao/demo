package com.corgi.mongodb.repository;

import com.corgi.mongodb.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @title: UserRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:19
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
