package com.corgi.test.java8.currying;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @title: Tree
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 10:44
 **/
@Data
@Accessors(chain = true)
public class Tree {

    private Integer id;

    private String name;

    private Integer pId;

    private List<Tree> children;
}
