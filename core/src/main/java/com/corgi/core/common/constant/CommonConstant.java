package com.corgi.core.common.constant;

/**
 * @description: 系统常量
 * @author: dengmiao
 * @create: 2019-04-10 11:20
 **/
public interface CommonConstant {

    //=========================响应状态码==========================//
    /**
     * 成功
     */
    Integer SC_OK_200 = 200;

    /**
     * 服务器错误
     */
    Integer SC_INTERNAL_SERVER_ERROR_500 = 500;

    //=========================系统日志==========================//
    /**
     * 操作类型
     */
    interface LogAction {
        /**
         * 新增
         */
        String ADD = "0";
        /**
         * 修改
         */
        String UPDATE = "1";
        /**
         * 添加或修改
         */
        String ADD_UPDATE = "2";
        /**
         * 删除
         */
        String DEL = "3";
        /**
         * 打开form
         */
        String OPEN_FORM = "4";
        /**
         * 查看form
         */
        String VIEW_FORM = "5";
        /**
         * form 添加或查看
         */
        String OPEN_VIEW_FORM = "6";
        /**
         * 查询list
         */
        String SELECT_LIST = "7";

        /**
         * 查询PAGE
         */
        String SELECT_PAGE = "8";
    }

    /**
     * 日志类别
     */
    interface LogType {
        /**
         * 登录/操作/调度任务
         */
        int LOGIN = 0, OPERATE = 2, scheduled = 2;
    }

    /**
     * 日志操作人类别
     */
    interface OperatorType {
        /**
         * 后台用户/手机用户/其他
         */
        int MANAGE = 0, MOBILE = 1, OTHER = 2;
    }
}
