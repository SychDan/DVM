package ru.mephi.kaf82.DVM.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by Vakobo
 */

public class TransactionFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(TransactionFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Transactional
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
