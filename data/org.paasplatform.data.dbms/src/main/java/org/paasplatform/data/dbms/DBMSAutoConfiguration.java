package org.paasplatform.data.dbms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration()
@EnableConfigurationProperties(DBMSProperties.class)
@ComponentScan("org.paasplatform.data.dbms.controller")
public class DBMSAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(HBMSchemaService.class)
    public ISchemaService schemaService(DBMSProperties properties) {
        System.out.printf("properties var is: %s", properties.getVar());
        return new HBMSchemaService();
    }
}
