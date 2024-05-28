package org.paasplatform.data.core;

import org.paasplatform.data.ext.postgres.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
        Test test= new Test();
        test.Do();
    }
}


