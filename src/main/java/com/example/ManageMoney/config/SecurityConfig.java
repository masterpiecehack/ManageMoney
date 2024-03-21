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

    /**
     * 認証機能オン
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // CSRF保護を無効化
                .headers().frameOptions().disable() // H2コンソールのフレームオプションを無効化
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll() // H2コンソールへのアクセスを許可
                .antMatchers("/login", "/signup", "/css/**", "/js/**", "/images/**").permitAll() // ログインページと静的リソースへのアクセスを許可
                .anyRequest().authenticated() // その他のリクエストは認証を要求
                .and()
                .formLogin()
                .loginPage("/login") // カスタムログインページの設定
                .defaultSuccessUrl("/mypage") // ログイン成功時のリダイレクト先
                .permitAll()
                .and()
                .logout().permitAll();

        return http.build();
    }

    /**
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
