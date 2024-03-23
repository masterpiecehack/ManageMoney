package com.example.ManageMoney.security;

import com.example.ManageMoney.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private Users user;

    // コンストラクタ
    public CustomUserDetails(Users user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // この例では、全ユーザーに 'ROLE_USER' を付与します。
        // 実際のアプリケーションでは、ユーザーに応じた権限を設定する必要があります。
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // アカウントの有効期限切れを管理する場合は、ここを適切に実装します。
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // アカウントがロックされていないかを管理する場合は、ここを適切に実装します。
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // 資格情報（パスワード）の有効期限を管理する場合は、ここを適切に実装します。
        return true;
    }

    @Override
    public boolean isEnabled() {
        // ユーザーが有効かどうかを管理する場合は、ここを適切に実装します。
        return true;
    }

    // 追加メソッド: User エンティティへのアクセスを提供します。
    public Users getUser() {
        return user;
    }
}
