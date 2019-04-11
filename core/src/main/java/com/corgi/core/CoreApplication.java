package com.corgi.core;

import com.corgi.core.common.toolkit.SpringContextUtil;
import com.corgi.core.config.auto.CorgiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.springframework.security.config.Elements.HTTP;

/**
 * 启动类
 * @author dengmiao
 */
@Slf4j
@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) throws UnknownHostException {
        //获取开始时间
        long start = System.currentTimeMillis();
        SpringApplication app = new SpringApplication(CoreApplication.class);
        Environment env = app.run(args).getEnvironment();
        String protocol = HTTP;
        //获取结束时间
        long end = System.currentTimeMillis();
        CorgiProperties corgiProperties = ((CorgiProperties) SpringContextUtil.getBean(CorgiProperties.class));
        log.info("\n----------------------------------------------------------\n\t"
                        + "名称:\t'{}' is running! Access URLs:\n\t" + "本地:\t {}://localhost:{}\n\t" + "外部:\t {}://{}:{}\n\t"
                        + "环境:\t {}\n\t" + "版本:\t {}\n\t" + "用时:\t {}\n----------------------------------------------------------",
                corgiProperties.getProject().getName(),
                protocol, env.getProperty("server.port"), protocol, InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"), env.getActiveProfiles(), corgiProperties.getProject().getVersion(), (end - start) + "ms");
    }

}
