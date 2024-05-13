package org.passplatform.edas.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurerAdapter 是一个实现了WebMvcConfigurer 接口的抽象类，并提供了全部方法的空实现，
 * 我们可以在其子类中覆盖这些方法，以实现我们自己的配置，如视图解析器，拦截器和跨域支持等...，
 * 由于Java的版本更新，在Java 8中，可以使用default关键词为接口添加默认的方法，Spring在升级的过程中也同步支持了Java 8中这一新特性。
 * 从Spring 5开始，WebMvcConfigure接口包含了WebMvcConfigurerAdapter类中所有方法的默认实现，因此WebMvcConfigurerAdapter这个适配器就被打入冷宫了
 */
@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = { "com.baeldung.contexts.normal" })
public class ModuleBWebApplicationConfiguration implements WebMvcConfigurer {
}
