package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@EnableScheduling
@MapperScan("com.cloud.mapper")
@EnableTransactionManagement
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

}
//public class BackendApplication implements CommandLineRunner {
//    @Resource
//    private CanalClient canalClient;
//    public static void main(String[] args) {
//        SpringApplication.run(BackendApplication.class, args);
//    }
//    @Override
//    public void run(String... strings) throws Exception {
//        //项目启动，执行canal客户端监听
//        canalClient.run();
//    }
//
//
//}

