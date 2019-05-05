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
public class Body implements Serializable {

    private Double stature;
    private Double weight;
    private Double cranialCapacity;
}
