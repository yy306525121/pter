package cn.codeyang.pter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(proxyBeanMethods = false)
public class PterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PterApplication.class, args);
    }

}
