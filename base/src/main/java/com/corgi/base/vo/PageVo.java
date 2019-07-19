package com.corgi.base.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 分页请求参数
 * @author: dengmiao
 * @create: 2019-04-11 17:20
 **/
@Data
public class PageVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页号")
    private Integer pageNo = 1;

    @ApiModelProperty(value = "页面大小")
    private Integer pageSize = 10;

    @ApiModelProperty(value = "排序字段")
    private String column;

    @ApiModelProperty(value = "排序方式 asc/desc")
    private String order = "asc";

    private String[] groupKey;

    private List<OrderVo> orderVoList;
}
