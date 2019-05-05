package com.corgi.base.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-05-02 09:09
 **/
@Data
@ApiModel
public class OrderVo implements Serializable {

    private String column;

    private String order = "asc";

    private boolean isConvert = false;
}
