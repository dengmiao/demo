package com.corgi.core.config.security.jwt;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * @description: 登录失败处理类
 * @author: dengmiao
 * @create: 2019-04-04 16:59
 **/
@Component
public class AuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {
}
