package com.bytesvc.consumer.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * 使用文件存储时, 不需要配置mongodb
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.bytesvc.consumer"}, exclude = {MongoAutoConfiguration.class})
public class SpringBootConsumerBootstrap {

    public static void main(String[] args) throws Throwable {
        SpringApplication application = new SpringApplication(SpringBootConsumerBootstrap.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        log.info("springboot consumer start success");
    }

}
