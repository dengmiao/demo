package com.corgi.core.config.auto;

/**
 * @description: corgi默认配置
 * @author: dengmiao
 * @create: 2019-04-11 11:21
 **/
public interface CorgiDefult {

    /**
     * 项目信息
     */
    interface Project {
        /**
         * 名称
         */
        String name = "Corgi-企业级脚手架";
        /**
         * 版本
         */
        String version = "v1.0.0 Alpha";
        /**
         *
         */
        String poweredBy = "https://github.com/dengmiao/demo";
    }
}
