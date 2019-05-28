package com.corgi.r2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

/**
 * @title: Coffee
 * @description:
 * @author: dengmiao
 * @create: 2019-05-28 15:22
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("t_coffee")
public class Coffee {

    @Id
    private Long id;

    private String name;

    private Money price;

    private Date createDate;

    private Date updateDate;
}
