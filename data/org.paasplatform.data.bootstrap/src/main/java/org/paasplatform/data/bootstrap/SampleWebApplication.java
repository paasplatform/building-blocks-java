package org.paasplatform.data.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SampleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleWebApplication.class, args);
    }
}
