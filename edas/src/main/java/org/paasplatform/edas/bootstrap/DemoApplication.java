package org.paasplatform.edas.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * The exclude does prevent Spring Boot from creating its own DispatcherServlet with / mapping.
 * You can remove that line, if you want that mapping or define your own.
 * --- CustomHttpServlet
 * @ServletComponentScan @WebServlet，@WebFilter，@WebListener
 * or by ServletRegistrationBean
 */
@SpringBootApplication(exclude= {DispatcherServletAutoConfiguration.class,SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "org.paasplatform.edas.configuration")
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
