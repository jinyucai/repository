package com.cai.config;

import com.cai.auth.*;
import com.cai.auth.sms.SmsCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //开启全局权限配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    private MyExpireSessionStrategy myExpireSessionStrategy;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private MyKaptchaFilter myKaptchaFilter;
    @Autowired
    private SmsCodeSecurityConfig smsCodeSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //basic认证很容易破解
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();
        http
                //将自定义验证码验证放在用户名密码验证前面
                .addFilterBefore(myKaptchaFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login.html", "/login", "/kaptcha", "/smscode", "/smslogin")
                .permitAll()
//                .antMatchers("/biz1", "/biz2")
//                .hasAnyAuthority("ROLE_admin")
                .antMatchers("/index", "/").authenticated()
                .antMatchers("/system/*").access("hasRole(admin) or hasAnyAuthority(ROLE_admin)")
                .anyRequest()
//                .authenticated()
                    //动态配置访问权限
                    .access("@rbacService.hasPermission(request, authentication)")
                .and()
                    .rememberMe()
                    .rememberMeParameter("remember-me")
                    .rememberMeCookieName("remember-me-cookie")
                    .tokenValiditySeconds(2*24*60*60)
                    .tokenRepository(persistentTokenRepository())
                .and()
                    //jwt实现前后端分离，不需要这些页面，自己实现token令牌
//                    .formLogin()
//                    .loginPage("/login.html")
//                    .permitAll()
//                    .loginProcessingUrl("/login")
//                    .defaultSuccessUrl("/index")
//    //                .failureUrl("/login.html") //自定义失败处理和这个只能有一个
//                    .failureHandler(myAuthenticationFailureHandler)
//                .and().apply(smsCodeSecurityConfig)
//                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .invalidSessionUrl("/login.html")
                    .sessionFixation()
                    .migrateSession()
                    .maximumSessions(1) //最多只能一个用户
                    .maxSessionsPreventsLogin(false)
                    .expiredSessionStrategy(myExpireSessionStrategy)
                .and()
                .and()
                    .logout()
                    .deleteCookies("JSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/js/**", "/static/css/**");
    }

    /**
     * 将记住密码token存到数据库
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
