package ru.mephi.kaf82.DVM.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class DetailsServiceImpl implements UserDetailsService {

    private String code="03324";

    /**
     * Авторизация. Пока только по коду.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (code.equals(login)) {
            return new User(login,"", getGranterAuthorities());
        } else {
            throw new UsernameNotFoundException(login + "  неверный");
        }
    }
    /**
     * Получение прав.
     */
    private ArrayList<GrantedAuthority> getGranterAuthorities() {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

}
