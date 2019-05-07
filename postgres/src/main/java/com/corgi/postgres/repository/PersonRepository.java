package com.corgi.postgres.repository;

import com.corgi.postgres.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
public interface PersonRepository extends JpaRepository<Person,Long> {
    /**
     * selectMap
     * @param tag
     * @param value
     * @return
     */
    @Query(value = "select * from public.person b where b.tags ->>?1 = ?2 ", nativeQuery = true)
    List<Person> selectByMap(String tag, String value);
}
