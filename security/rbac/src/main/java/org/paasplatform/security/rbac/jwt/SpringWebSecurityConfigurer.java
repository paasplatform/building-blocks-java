package org.paasplatform.security.rbac.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SpringWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

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
        // 开启登录配置
        http.authorizeRequests()

            //.antMatchers("/user/**").hasRole("User")// 需要具有ROLE_USER角色才能访问
            .antMatchers("/admin/**").hasAnyAuthority("WRITE_PRIVILEGE")//.hasRole("Admin"); // 需要具有ROLE_ADMIN角色才能访问
                .antMatchers("/users/signin").permitAll()
            .anyRequest()
            .authenticated();

        // Disable CSRF (cross site request forgery)
        http.csrf().disable();

        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Apply JWT
        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }

    /**
     * Spring Security ignores URLs of static resources
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
                "/webjars/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
