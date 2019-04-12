package com.corgi.core.modules.sys.model;

import com.corgi.core.modules.sys.entity.SysDept;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 10:31
 **/
@Data
public class SysDeptTreeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 对应SysDepart中的id字段,前端数据树中的key*/
    private String key;

    /** 对应SysDepart中的id字段,前端数据树中的value*/
    private String value;

    /** 对应depart_name字段,前端数据树中的title*/
    private String title;


    // 以下所有字段均与SysDepart相同

    private String id;

    private String parentId;

    private String departName;

    private String departNameEn;

    private String departNameAbbr;

    private Integer departOrder;

    private Object description;

    private String orgType;

    private String orgCode;

    private String mobile;

    private String fax;

    private String address;

    private String memo;

    private String status;

    private String delFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private List<SysDeptTreeModel> children = new ArrayList<>();

    /**
     * 将SysDepart对象转换成SysDepartTreeModel对象
     * @param sysDept
     */
    public SysDeptTreeModel(SysDept sysDept) {
        this.key = String.valueOf(sysDept.getId());
        this.value = String.valueOf(sysDept.getId());
        this.title = sysDept.getDepartName();
        this.id = String.valueOf(sysDept.getId());
        Long parentId = sysDept.getParentId();
        this.parentId = parentId != null ? String.valueOf(parentId) : null;
        this.departName = sysDept.getDepartName();
        this.departNameEn = sysDept.getDepartNameEn();
        this.departNameAbbr = sysDept.getDepartNameAbbr();
        this.departOrder = sysDept.getDepartOrder();
        this.description = sysDept.getDescription();
        this.orgType = sysDept.getOrgType();
        this.orgCode = sysDept.getOrgCode();
        this.mobile = sysDept.getMobile();
        this.fax = sysDept.getFax();
        this.address = sysDept.getAddress();
        this.memo = sysDept.getMemo();
        this.status = String.valueOf(sysDept.getStatus());
        this.delFlag = String.valueOf(sysDept.getDelFlag());
        this.createBy = sysDept.getCreateBy();
        this.createTime = sysDept.getCreateTime();
        this.updateBy = sysDept.getUpdateBy();
        this.updateTime = sysDept.getUpdateTime();
    }

    public static void main(String[] args) {
        System.out.println(String.valueOf(null));
    }
}
