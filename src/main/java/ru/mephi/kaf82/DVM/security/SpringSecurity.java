package ru.mephi.kaf82.DVM.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by Vakobo
 */

public class SpringSecurity extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SpringSecurity.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
