package com.corgi.core.config.security;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-04 17:21
 **/
public interface SecurityConstant {

    /**
     * token分割
     */
    String TOKEN_SPLIT = "Bearer ";

    /**
     * JWT签名加密key
     */
    String JWT_SIGN_KEY = "corgi";

    /**
     * token参数头
     */
    String HEADER = "accessToken";

    /**
     * 权限参数头
     */
    String AUTHORITIES = "authorities";

    /**
     * 用户选择JWT保存时间参数头
     */
    String SAVE_LOGIN = "saveLogin";

    /**
     * 交互token前缀key
     */
    String TOKEN_PRE = "CORGI_TOKEN_PRE:";

    /**
     * 用户token前缀key 单点登录使用
     */
    String USER_TOKEN = "CORGI_USER_TOKEN:";
}
