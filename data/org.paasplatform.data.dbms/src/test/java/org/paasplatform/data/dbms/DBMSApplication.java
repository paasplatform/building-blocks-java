package org.paasplatform.data.dbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DBMSApplication {
    public static void main(String[] args) {
        SpringApplication.run(DBMSApplication.class, args);
        new HBMSchemaService().execute();
    }
}
