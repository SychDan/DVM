package ru.mephi.kaf82.DVM.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.mephi.kaf82.DVM.model.Person;
import ru.mephi.kaf82.DVM.repository.PersonRepository;

import javax.annotation.Resource;

public class AuthService {

    @Resource
    private PersonRepository personRepository;

    public Person getLoginedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return personRepository.findByCardNumber(authentication.getName());
    }
}
