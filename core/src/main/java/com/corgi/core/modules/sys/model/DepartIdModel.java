package com.corgi.core.modules.sys.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 10:47
 **/
@Data
public class DepartIdModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String key;

    /**
     * 主键ID
     */
    private String value;

    /**
     * 部门名称
     */
    private String title;

    List<DepartIdModel> children = new ArrayList<>();

    /**
     * 将SysDeptTreeModel的部分数据放在该对象当中
     * @param treeModel
     * @return
     */
    public DepartIdModel convert(SysDeptTreeModel treeModel) {
        this.key = treeModel.getId();
        this.value = treeModel.getId();
        this.title = treeModel.getDepartName();
        return this;
    }
}
