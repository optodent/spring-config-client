package com.rusev.cloud.springconfigclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringConfigClientApplication.class, args);
    }

}

@RefreshScope
@RestController
class MessageRestController {

    @Autowired
    public void setEnv(Environment e) {
        System.out.println(e.getProperty("msg"));
    }

    @Value("${customer.environment.variable:cannot load variable, initialization error}")
    private String msg;

    @GetMapping("/msg")
    public String getMsg() {
        return this.msg;
    }
}