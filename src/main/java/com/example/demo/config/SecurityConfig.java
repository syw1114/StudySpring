package com.example.demo.config;

import com.example.demo.security.AuthFailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity // 이걸 해줘야 빈객체로 시큐리티에 등록이된다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.authorizeRequests()
                .antMatchers("/account/mypage", "/index")
                .authenticated()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .loginPage("/account/Login")
                .loginProcessingUrl("/account/Login") // 포스트요청
                .failureHandler(new AuthFailureHandler())
                .defaultSuccessUrl("/index"); // 돌려줄곳이없으면 여기로
    }
}
