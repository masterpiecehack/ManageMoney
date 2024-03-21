package com.example.ManageMoney.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ManageMoney.repository.UserRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository; // ユーザー情報を管理するリポジトリ

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // userRepositoryからユーザーを検索
        com.example.ManageMoney.entity.User user = userRepository.findByUsername(username);

        // ユーザーがnullの場合、UsernameNotFoundExceptionをスロー
        if(user == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // ユーザーが見つかった場合、UserDetailsオブジェクトを構築して返す
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // パスワードはエンコードされている必要があります
                .roles("USER") // 適切なロールを設定
                .build();
    }
}



