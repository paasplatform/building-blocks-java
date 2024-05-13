package org.paasplatform.edas.configuration;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Spring Boot with multiple DispatcherServlet, each having their own
 *  :ApplicationContext(DispatcherServlet)
 *  :@Controllers
 *  :Multi Security configuration: HttpSecurityConfig/WebSecurityConfigurerAdapter
 * But only one root: WebApplicationContext by ContextLoadListener
 */
@Configuration
public class DispatcherServletConfiguration {
    @Bean
    @Primary
    public DispatcherServletRegistrationBean ModuleA() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ModuleAWebApplicationConfiguration.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        DispatcherServletRegistrationBean servletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/modulea/*");
        //You can add servletRegistrationBean.setLoadOnStartup(1) if you want to have your Servlets initialized on application start. Else it will wait for the first request for that servlet.
        servletRegistrationBean.setLoadOnStartup(0);
        //It's important to set servletRegistrationBean.setName(...), else the servlets will override each other.
        servletRegistrationBean.setName("moduleA");
        return servletRegistrationBean;
    }
    @Bean
    public DispatcherServletRegistrationBean ModuleB() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(ModuleBWebApplicationConfiguration.class);
        dispatcherServlet.setApplicationContext(applicationContext);
        DispatcherServletRegistrationBean servletRegistrationBean = new DispatcherServletRegistrationBean(dispatcherServlet, "/moduleb/*");
        //servletRegistrationBean.setLoadOnStartup(0);
        servletRegistrationBean.setName("moduleB");
        return servletRegistrationBean;
    }
}
