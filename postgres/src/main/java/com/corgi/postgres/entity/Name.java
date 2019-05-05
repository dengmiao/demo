package com.corgi.postgres.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Name implements Serializable {

    private String xing;
    private String ming;
    private String zi;
    private String title;
}
