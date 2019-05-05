package com.corgi.postgres.repository;

import com.corgi.postgres.constant.GeneralConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@Component
public class MainRepository {



    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    /**
     * 拼接字符串查询
     * @param map
     * @param c
     * @return
     */
    public  List tagQuery(HashMap map,Class c) {
        if(map.size() < 1){
            return null;
        }
        EntityManager em = entityManagerFactory.getNativeEntityManagerFactory().createEntityManager();
        String sql = "select * from "+ GeneralConstants.ledgerSchema+"."+GeneralConstants.studyTable+" s where ";
        StringBuilder content = new StringBuilder();
        map.forEach((k,v)->{
            content.append("s.tags->> '"+k+"' = '"+v+"' and ");
        });
        content.append(" 1=1");
        sql += content.toString();
        Query query = em.createNativeQuery(sql, c);
        List resultList = query.getResultList();
        em.close();
        return resultList;
    }
}
