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
    public Object test01(@RequestParam(defaultValue = "tag1") String key, @RequestParam(defaultValue = "value1")String value){
        return repository.selectMap(key,value);
    }
    @RequestMapping("add")
    public Object test02(){
        Person person = new Person();
        Body body = new Body()
                .setStature(2.82).setWeight(62.0).setHeight(175.0);
        person.setBody(body);
        Name name = new Name()
                .setLastName("miao").setFirstName("deng").setNickName("邓半仙").setTitle("山支大哥");
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

        return repository.save(person);
    }
    @GetMapping("test03")
    public Object test03(){
        return null;
    }

    @GetMapping("test")
    public Object test05(){
        EntityManager em = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        String sql = "select * from public.person b where b.tags ->>'tag1'= 'value1'";
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
