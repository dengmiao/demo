package com.corgi.mongo.repository;

import com.corgi.mongodb.MongodbApplication;
import com.corgi.mongodb.entity.Body;
import com.corgi.mongodb.repository.BodyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @title: BodyRepositoryTests
 * @description:
 * @author: dengmiao
 * @create: 2019-06-10 09:26
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MongodbApplication.class})
public class BodyRepositoryTests {

    @Autowired
    private BodyRepository bodyRepository;

    @Test
    public void add() {
        bodyRepository.save(new Body().setHair("black").setHead(250l).setLeg("180"));
    }
}
