package org.paasplatform.security.rbac.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.PrintWriter;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SpringWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

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
            .antMatchers("/authentication/*", "/login").permitAll()
            .antMatchers("/user/**").hasAnyAuthority("read")// 需要具有ROLE_USER角色才能访问
            .antMatchers("/admin/**").hasAnyAuthority("WRITE_PRIVILEGE") // 需要具有ROLE_ADMIN角色才能访问
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/authentication/login") // 设置登录页面
                .loginProcessingUrl("/login1")
                .defaultSuccessUrl("/user/index") // 设置默认登录成功后跳转的页面
                // 自定义登陆用户名和密码属性名，默认为 username和password
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录成功后的处理器  - 方式②
                /*.successHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("登录成功...");
                    out.flush();
                })*/
                // 配置登录失败的回调
                .failureHandler((req, resp, exception) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("登录失败...");
                    out.flush();
                })//.authenticationDetailsSource(authenticationDetailsSource)
            .permitAll()//和表单登录相关的接口统统都直接通过
                .and()
            .logout().logoutUrl("/logout")
                // 配置注销成功的回调
                .logoutSuccessHandler((req, resp, authentication) -> {
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write("注销成功...");
                    out.flush();
                })
            .permitAll()
                .and()
            .httpBasic()
                .and()
                // 关闭CSRF跨域
            .csrf().disable();
    }

    /**
     * Spring Security ignores URLs of static resources
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
