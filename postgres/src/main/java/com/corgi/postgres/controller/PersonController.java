package com.corgi.postgres.controller;

import com.corgi.postgres.entity.Body;
import com.corgi.postgres.entity.Name;
import com.corgi.postgres.entity.Person;
import com.corgi.postgres.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@RestController
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @GetMapping
    public Object test01(@RequestParam(defaultValue = "12344") String key, @RequestParam(defaultValue = "asdfasd")String value){
        /*select * from public.person b where b.tags ->>'4545' = 'gfdgdfg'
        select * from public.person b where b."name" ->> 'zi' = '孔明'*/
        return repository.selectMap(key,value);
    }
    @RequestMapping("add")
    public Object test02(){
        Person person = new Person();
        Body body = new Body();
        body.setWeight(62.0);
        body.setStature(175.0);
        body.setCranialCapacity(999999.9);
        person.setBody(body);
        Name name = new Name();
        name.setMing("zhenyang");
        name.setXing("lu");
        name.setTitle("灵宝天尊");
        name.setZi("孔明");
        person.setName(name);
        HashMap<String,String> map = new HashMap<String,String>(8);
        map.put("12344","asdfasd");
        map.put("4545","gfdgdfg");
        person.setTags(map);
        List<String> wifes = new ArrayList<>();
        wifes.add("乐正绫");
        wifes.add("双笙子");
        wifes.add("柳梦璃");
        person.setWifes(wifes);

        return repository.save(person);
    }
    @GetMapping("test03")
    public Object test03(){
        return null;
    }

    @GetMapping("test")
    public Object test05(){
        EntityManager em = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        String sql = "select * from public.person b where b.tags ->>'12344'= 'asdfasd'";
        Query query = em.createNativeQuery(sql, Person.class);
        List resultList = query.getResultList();
        return resultList;
    }

    @PutMapping
    public Object test06() {
        return null;
    }
    @DeleteMapping
    public Object test07(){
        return null;
    }
}
