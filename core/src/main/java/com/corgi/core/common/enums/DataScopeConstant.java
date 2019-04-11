package com.corgi.core.common.enums;

/**
 * @description: 数据范围枚举
 * @author: dengmiao
 * @create: 2019-04-11 11:03
 **/
public enum DataScopeConstant {

    /**
     * 所有数据
     */
    DATA_SCOPE_ALL("1", "所有数据"),
    /**
     * 所在公司及以下数据
     */
    DATA_SCOPE_COMPANY_AND_CHILD("2", "所在公司及以下数据"),
    /**
     *所在公司数据
     */
    DATA_SCOPE_COMPANY("3", "所在公司数据"),
    /**
     * 所在部门及以下数据
     */
    DATA_SCOPE_ORG_AND_CHILD("4", "所在部门及以下数据"),
    /**
     * 所在部门数据
     */
    DATA_SCOPE_ORG("5", "所在部门数据"),
    /**
     * 仅本人数据
     */
    DATA_SCOPE_SELF("8", "仅本人数据"),
    /**
     * 按明细设置
     */
    DATA_SCOPE_CUSTOM("9", "按明细设置");

    private String number;
    private String describe;

    DataScopeConstant(String number, String describe) {
        this.number = number;
        this.describe = describe;
    }

    public String getNumber() {
        return number;
    }

    public String getDescribe() {
        return describe;
    }
}
