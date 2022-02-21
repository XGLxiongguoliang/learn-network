package com.example.network.framework.jwt;

import com.example.network.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Collections;

/**
 * @program com.example.network.framework.jwt
 * @description jwtUserInfo
 * @auther Mr.Xiong
 * @create 2021-08-14 13:17:07
 */
@Getter
@Setter
public class JwtUser implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtUser(UserInfo user) {
        id = user.getId();
        username = user.getUsername();
        password = new BCryptPasswordEncoder().encode(user.getPassword());
        //这里说明一下，必须要加上ROLE_开头，或者在数据库直接以这个开头
        authorities = Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    // 获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
