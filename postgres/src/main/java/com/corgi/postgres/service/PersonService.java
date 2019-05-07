package com.corgi.postgres.service;

import com.corgi.postgres.entity.Person;
import com.corgi.postgres.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: PersonService
 * @description:
 * @author: dengmiao
 * @create: 2019-05-07 09:18
 **/
@Service
public class PersonService {

    private final PersonRepository repository;

    public PersonService(final PersonRepository repository) {
        this.repository = repository;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Person> save(Person person) {
        return Mono.justOrEmpty(repository.save(person));
    }

    public Mono<Person> findById(final Long id) {
        /*Person person = repository.getOne(id);
        System.out.println(person);*/
        return Mono.justOrEmpty(repository.findById(id));
    }

    public Mono findByMap(String tag, String value) {
        return Mono.justOrEmpty(repository.selectByMap(tag, value));
    }

    public Flux<Person> findList() {
        return Flux.fromIterable(repository.findAll());
    }
}
