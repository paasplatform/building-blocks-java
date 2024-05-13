package org.paasplatform.edas.configuration;

import org.paasplatform.edas.configuration.listener.CustomServletContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;

@Configuration
public class ServletListenerConfiguration {
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> customListenerBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean = new ServletListenerRegistrationBean();
        bean.setListener(new CustomServletContextListener());
        return bean;
    }
}
