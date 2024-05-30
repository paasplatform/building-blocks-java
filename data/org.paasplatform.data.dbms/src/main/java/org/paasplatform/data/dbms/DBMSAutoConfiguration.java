package org.paasplatform.data.dbms;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
@EnableConfigurationProperties(DBMSProperties.class)
public class DBMSAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(HBMSchemaService.class)
    public HBMSchemaService schemaService(DBMSProperties properties) {
        
        return new HBMSchemaService();
    }
}
