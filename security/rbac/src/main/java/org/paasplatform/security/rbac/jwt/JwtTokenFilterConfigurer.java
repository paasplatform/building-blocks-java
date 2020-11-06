package org.paasplatform.security.rbac.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        //this.authenticatBasicAuthenticationFilterionFilter=authenticationFilter;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
        http.addFilterBefore(customFilter, BasicAuthenticationFilter.class);

        //http.addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
        //        .addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}