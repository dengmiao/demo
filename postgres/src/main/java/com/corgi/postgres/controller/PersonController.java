package com.corgi.postgres.controller;

import com.corgi.base.vo.Result;
import com.corgi.postgres.entity.Body;
import com.corgi.postgres.entity.Name;
import com.corgi.postgres.entity.Person;
import com.corgi.postgres.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@Slf4j
@RestController
@RequestMapping("postgres/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @GetMapping("block")
    public Result block() {
        long start = System.currentTimeMillis();
        Result result = Result.ok(doQuery());
        log.info("block: {}", System.currentTimeMillis() - start);
        return result;
    }

    @GetMapping("reactive")
    public Mono<Result> reactive() {
        long start = System.currentTimeMillis();
        // 惰性求值
        Mono<Result> mono = Mono.fromSupplier(() -> Result.ok(doQuery()));
        log.info("reactive: {}", System.currentTimeMillis() - start);
        return mono;
    }

    @PostMapping("void")
    public Mono<Result> Void() {
        return Mono.just(Result.ok());
    }

    private Object doQuery() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello postgres!";
    }

    @GetMapping("test01")
    public Mono<Result> test01(@RequestParam(defaultValue = "tag1") String key, @RequestParam(defaultValue = "value1")String value){
        return Mono.just(Result.ok(personService.findByMap(key,value)));
    }

    @GetMapping("getById/{id}/")
    public Mono<Result> getById(@PathVariable("id") final Long id) {
        return Mono.just(Result.ok(personService.findById(id)));
    }

    @RequestMapping("add")
    public Mono<Result> add(){
        Person person = new Person();
        Body body = new Body()
                .setStature(2.82).setWeight(62.0).setHeight(175.0);
        person.setBody(body);
        Name name = new Name()
                .setLastName("miao").setFirstName("deng").setNickName("半仙").setTitle("山支大哥");
        person.setName(name);
        HashMap<String,String> map = new HashMap<String,String>(8){
            {
                put("tag1","value1");
                put("tag2","value2");
            }
        };
        person.setTags(map);
        List<String> wives = new ArrayList<String>(){
            {
                add("孟美岐");
                add("御坂美琴");
                add("西尔莎·罗南");
            }
        };
        person.setWives(wives);

        return Mono.just(Result.ok(personService.save(person)));
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Person> getList() {
        return Flux.fromIterable(personService.findList()).delayElements(Duration.ofSeconds(1));
    }

    @GetMapping("test05")
    public Result<Object> test05(){
        EntityManager em = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        String sql = "select * from public.person1 b where b.tags ->>'tag1'= 'value1'";
        Query query = em.createNativeQuery(sql, Person.class);
        List resultList = query.getResultList();
        return Result.ok(resultList);
    }

    @PutMapping("test06")
    public Object test06() {
        return null;
    }
    @DeleteMapping("test07")
    public Object test07(){
        return null;
    }
}
