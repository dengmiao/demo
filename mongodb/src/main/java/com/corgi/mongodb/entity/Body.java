package com.corgi.mongodb.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @title: Body
 * @description:
 * @author: dengmiao
 * @create: 2019-06-10 09:19
 **/
@Data
@Accessors(chain = true)
@Document(collection = "body")
public class Body {

    private String id;

    private Long head;

    private String hair;

    private String leg;
}
