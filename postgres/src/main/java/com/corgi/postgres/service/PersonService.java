package com.corgi.postgres.service;

import com.corgi.postgres.entity.Person;
import com.corgi.postgres.repository.PersonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

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
    public Person save(Person person) {
        return repository.save(person);
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<Person> findById(final Long id) {
        Person person = repository.getOne(id);
        System.out.println(person);
        return repository.findById(id);
    }

    public List<Person> findByMap(String tag, String value) {
        return repository.selectByMap(tag, value);
    }

    public List<Person> findList() {
        return repository.findAll();
    }
}
