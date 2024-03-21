package com.example.ManageMoney.config;

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
                .authorizeRequests(auth -> auth
//                        .antMatchers("/", "/home").permitAll() // "/" と "/home" へのアクセスを許可
                        .anyRequest().authenticated() // それ以外のリクエストは認証が必要
                )
                .formLogin(form -> form
                        .loginPage("/login") // カスタムログインページのパス
                        .permitAll() // すべてのユーザーにログインページへのアクセスを許可
                )
                .logout(logout -> logout
                        .permitAll() // すべてのユーザーにログアウトを許可
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}