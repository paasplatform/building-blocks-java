package org.paasplatform.security.rbac;

import org.paasplatform.security.rbac.jpa.UserRepository;
import org.paasplatform.security.rbac.jwt.JwtAccessDeniedHandler;
import org.paasplatform.security.rbac.jwt.JwtAuthenticationEntryPoint;
import org.paasplatform.security.rbac.jwt.JwtTokenFilterConfigurer;
import org.paasplatform.security.rbac.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SpringWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationErrorHandler;

    @Autowired
    private JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Autowired
    private CorsFilter corsFilter;

    /* @Autowired
     private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

     @Autowired
     private LogoutSuccessHandler myLogoutSuccessHandler;

     @Autowired
     private AuthenticationFailureHandler authenticationFailureHandler;
 */
    @Autowired
    private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

    @Autowired
    private UserRepository userRepository;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        final CustomAuthenticationProvider authProvider = new CustomAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        //authProvider.setPostAuthenticationChecks(differentLocationChecker);
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // we don't need CSRF because our token is invulnerable
            .csrf().disable()

            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

            .exceptionHandling()
            .authenticationEntryPoint(authenticationErrorHandler)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // create no session
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            .and()
            .authorizeRequests()
            .antMatchers("/users/signin").permitAll()
            .antMatchers("/admin/index").hasAuthority("ROLE_USER")
            // .antMatchers("/api/activate").permitAll()
            // .antMatchers("/api/account/reset-password/init").permitAll()
            // .antMatchers("/api/account/reset-password/finish").permitAll()

            .antMatchers("/api/person").hasAuthority("ROLE_USER")
            //.antMatchers("/api/hiddenmessage").hasAuthority("ROLE_ADMIN")

            .anyRequest().authenticated()

            .and()
            .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    /**
     * Spring Security ignores URLs of static resources
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());

        web.ignoring().antMatchers(HttpMethod.GET,
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**")

        // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
        .and()
                .ignoring()
                .antMatchers("/h2-console/**/**");;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
