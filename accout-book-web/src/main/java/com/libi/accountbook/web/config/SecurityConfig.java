package com.libi.accountbook.web.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.libi.accountbook.web.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.libi.accountbook.web.constant.UrlConst.*;

/**
 * @author libi
 */
@Configuration
@ComponentScan(basePackages = {"com.libi.accountbook.web"})
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 密码编码
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    /**
     * 配置整个用户信息从哪里获得
     */
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                //密码加密
                .passwordEncoder(passwordEncoder());
    }

    @Override
    /**
     * 安全配置的详细信息
     */
    protected void configure(final HttpSecurity http) throws Exception {
        http    //下面是详细的安全性调整
                .authorizeRequests()
                //登录界面不限制，登出和查询用户界面需要登录才能访问
                    .antMatchers(LOGIN_URL,LOGIN_FAIL_URL,LOGOUT_SUCCESS_URL,REGISTER_URL,TEST_URL).permitAll()
                    .antMatchers("/**").authenticated()

                //设置登陆请求的URL
                .and().formLogin()
                .loginProcessingUrl(LOGIN_URL)
                .successForwardUrl(LOGIN_SUCCESS_URL)
                .failureForwardUrl(LOGIN_FAIL_URL)
                .usernameParameter(USERNAME_PARAM)
                .passwordParameter(PASSWORD_PARAM)
                //设置登出
                .and().logout().logoutUrl(LOGOUT_URL)
                .logoutSuccessUrl(LOGOUT_SUCCESS_URL)
                .deleteCookies("JSESSIONID")
                //设置拒绝时候的url
                .and().exceptionHandling().accessDeniedPage("/error/403")
                //设置未登录的操作
                .and().exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                if (httpServletRequest.getCookies() != null && httpServletRequest.getCookies().length > 0) {
                    logger.info("未登录的cookie:" + httpServletRequest.getCookies()[0].getName() + ":" + httpServletRequest.getCookies()[0].getValue());
                }
                logger.info("未登录访问的URI：" + httpServletRequest.getRequestURI() + " 方法：" + httpServletRequest.getMethod());
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                String sb = "{\"code\":10002,\"message\":\"未登录\",\"data\":null}";
                out.write(sb);
                out.flush();
                out.close();
            }
        })
                //TODO 暂时关闭csrf
                .and().csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

}
