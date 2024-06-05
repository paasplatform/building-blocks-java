package org.paasplatform.data.meta;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration()
public class MetaDataAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(JDBCMetaDataService.class)
    public JDBCMetaDataService metaDataService() {
        return new JDBCMetaDataService();
    }
}
