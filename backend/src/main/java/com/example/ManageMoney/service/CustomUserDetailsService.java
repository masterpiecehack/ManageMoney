package com.example.ManageMoney.service;

import com.example.ManageMoney.entity.Users;
import com.example.ManageMoney.repository.UsersRepository;
import com.example.ManageMoney.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Spring Securityのユーザー認証プロセスにおいて、
 * ユーザーの詳細情報をロードするためのサービスを提供します。
 * {@link UserDetailsService} インターフェースを実装し、
 * ユーザー名に基づいて {@link UserDetails} を取得します。
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    /**
     * 指定されたユーザー名に該当するユーザーを検索し、その詳細情報を取得します。
     * @param username ロードするユーザーのユーザー名
     * @return 認証されたユーザーの{@link UserDetails}オブジェクト
     * @throws UsernameNotFoundException 指定されたユーザー名を持つユーザーが見つからない場合
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new CustomUserDetails(user);
    }

    /**
     * 新しいメールアドレスが既に存在するかチェックする.
     * @param email 新しいメールアドレス
     * @return メールアドレスが存在する場合は true、そうでない場合は false
     */
    public boolean emailExists(String email) {
        return usersRepository.findByEmail(email) != null;
    }

    /**
     * 指定されたユーザーIDのユーザーのメールアドレスとパスワードを更新します。
     *
     * @param userId 更新するユーザーのID
     * @param newEmail 新しいメールアドレス
     * @param newEncodedPassword 新しいパスワード（このメソッドを呼び出す場合はエンコードされたパスワードを渡してください）
     */
    @Transactional
    public void updateUser(Long userId, String newEmail, String newEncodedPassword) {
        Users user = usersRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
        user.setEmail(newEmail);
        user.setPassword(newEncodedPassword);
        usersRepository.save(user);
    }
}
