package com.example.ManageMoney.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF保護を無効化
                .headers().frameOptions().disable() // H2コンソールのフレームオプションを無効化
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // H2コンソールへのアクセスを許可
                .antMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // ログインページと静的リソースへのアクセスを許可
                .anyRequest().authenticated() // その他のリクエストは認証を要求
                .and()
                .formLogin()
                .loginPage("/login").permitAll() // カスタムログインページを設定
                .and()
                .logout().permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
