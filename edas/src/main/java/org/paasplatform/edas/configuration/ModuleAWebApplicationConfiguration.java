package org.paasplatform.edas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurerAdapter 是一个实现了WebMvcConfigurer 接口的抽象类，并提供了全部方法的空实现，
 * 我们可以在其子类中覆盖这些方法，以实现我们自己的配置，如视图解析器，拦截器和跨域支持等...，
 * 由于Java的版本更新，在Java 8中，可以使用default关键词为接口添加默认的方法，Spring在升级的过程中也同步支持了Java 8中这一新特性。
 * 从Spring 5开始，WebMvcConfigure接口包含了WebMvcConfigurerAdapter类中所有方法的默认实现，因此WebMvcConfigurerAdapter这个适配器就被打入冷宫了
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.paasplatform.edas.moduleA" })
public class ModuleAWebApplicationConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //registry.addInterceptor(permissionCheckInterceptor).addPathPatterns("/admin/*").addPathPatterns("").excludePathPatterns("/index", "/");
    }

    /**
     * Spring Boot Async异步执行任务
     * configureAsyncSupport
     * WebAsyncManager是Spring MVC管理async processing的中心类
     * 默认是使用SimpleAsyncTaskExecutor,这个会为每次请求创建一个新的线程
     *
     * 我们可以配置async 的线程池，不需要为每个任务单独指定
     * 通过configurer.setTaskExecutor(threadPoolTaskExecutor());来指定线程池。
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(final AsyncSupportConfigurer configurer) {
        configurer.setDefaultTimeout(60 * 1000L);
        configurer.registerCallableInterceptors(timeoutInterceptor());
        configurer.setTaskExecutor(threadPoolTaskExecutor());
    }

    @Bean
    public TimeoutCallableProcessingInterceptor timeoutInterceptor() {
        return new TimeoutCallableProcessingInterceptor();
    }

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor t = new ThreadPoolTaskExecutor();
        t.setCorePoolSize(10);
        t.setMaxPoolSize(50);
        t.setThreadNamePrefix("YJH");
        return t;
    }
}
