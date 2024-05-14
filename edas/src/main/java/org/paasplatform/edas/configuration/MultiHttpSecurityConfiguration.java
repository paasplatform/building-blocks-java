package org.paasplatform.edas.configuration;

import org.paasplatform.security.rbac.SpringWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.web.context.request.async.WebAsyncTask;

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
    public static class ModuleAWebSecurityConfigurerAdapter extends SpringWebSecurityConfigurer {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    // we don't need CSRF because our token is invulnerable
//                    .antMatcher("/modulea/**")
//                    .csrf().disable()
//            .logout().disable()
//            .securityContext().disable()
//            .headers().disable()
//                    .securityContext().disable()
//                    .anonymous().disable()
//                    .requestCache().disable()
//                    .sessionManagement().disable()
//                    .exceptionHandling().disable();
//        }
    }

    @Configuration
    @Order(2)
    public static class ModuleBWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    // we don't need CSRF because our token is invulnerable
                    .antMatcher("/moduleb/**");

        }
    }
}
