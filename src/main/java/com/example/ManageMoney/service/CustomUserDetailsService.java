package com.example.ManageMoney.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ManageMoney.repository.UsersRepository;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository; // ユーザー情報を管理するリポジトリ

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // userRepositoryからユーザーを検索
        com.example.ManageMoney.entity.Users users = usersRepository.findByUsername(username);

        // ユーザーがnullの場合、UsernameNotFoundExceptionをスロー
        if(users == null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // ユーザーが見つかった場合、UserDetailsオブジェクトを構築して返す
        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword()) // パスワードはエンコードされている必要があります
                .roles("USER") // 適切なロールを設定
                .build();
    }
}



