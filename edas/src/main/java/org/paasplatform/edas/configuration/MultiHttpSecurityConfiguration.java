package org.passplatform.edas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configure spring security for multiple servlets
 * Enable the security
 * Enable security for the servlet 1
 * Enable security for the servlet 2...n
 */
@Configuration
/**
 * The @EnableWebSecurity annotation is crucial if we disable the default security configuration.
 * If missing, the application will fail to start. The annotation is only optional if we're just overriding the default behavior using a WebSecurityConfigurerAdapter.
 */
@EnableWebSecurity(debug = true)
public class MultiHttpSecurityConfiguration {
    /**
     * The name of the configureGlobal method is not important.
     * However, it is important to only configure AuthenticationManagerBuilder in a class annotated with either @EnableWebSecurity, @EnableWebMvcSecurity, @EnableGlobalMethodSecurity, or @EnableGlobalAuthentication. Doing otherwise has unpredictable results.
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    }

    @Configuration
    @Order(1)
    public static class ModuleAWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.antMatcher("/a/**")
                    .authorizeRequests()
                    .anyRequest().hasRole("ADMIN")
                    .and()
                    .httpBasic();
        }


    }

    @Configuration
    @Order(2)
    public static class ModuleBWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin();
        }
    }
}
