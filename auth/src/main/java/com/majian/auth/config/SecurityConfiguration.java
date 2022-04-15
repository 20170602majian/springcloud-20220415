package com.majian.auth.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //密码加密
    /*
    *
    * 2.1版本之后不允许明文密码,密码必须是加密的,使用此bean对密码,secret加密
    * */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    //@Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                                            //对密码进行加密   passwordEncoder.encode()
                .withUser("admin").password(passwordEncoder().encode("123456")).roles("ADMIN")
                .and()
                                            //对密码进行加密
                .withUser("user").password(passwordEncoder().encode("123456")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()//系统中所有请求
                .antMatchers("/**")//SpringSecurity接管的请求/**系统所有请求
                .and()
                .authorizeRequests()//得到SpringSecurity接管的请求
                .antMatchers("/test/*")//给接管的请求(/**)中的/test/*
                .hasAnyAuthority("p3")//配置需要p1权限
                .antMatchers("/mbb/*")//给接管的请求(/**)中的/mbb/*
                .permitAll()//放行，无需权限
                .anyRequest()//其他请求
                .authenticated()//都需要认证
                .and()
                .csrf().disable()//关闭csrf
        ;
    }

}

