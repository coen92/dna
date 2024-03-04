package com.coen92.valueobject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestValueObjectApplication {

    public static void main(String[] args) {
        SpringApplication.from(ValueObjectModule::main).with(TestValueObjectApplication.class).run(args);
    }

}
