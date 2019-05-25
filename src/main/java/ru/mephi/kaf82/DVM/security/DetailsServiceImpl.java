package ru.mephi.kaf82.DVM.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.mephi.kaf82.DVM.repository.PersonRepository;

import javax.annotation.Resource;
import java.util.ArrayList;

public class DetailsServiceImpl implements UserDetailsService {

    @Resource
    private PersonRepository personRepository;

    /**
     * Авторизация. Пока только по коду.
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if (personRepository.findByCardNumber(login) != null) {
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
