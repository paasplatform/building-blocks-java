package org.paasplatform.security.rbac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityAutoConfiguration {

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
////        authProvider.setPasswordEncoder(passwordEncoder());
//        //authProvider.setPostAuthenticationChecks(differentLocationChecker);
//        return authProvider;
//    }
}
