package com.sepehr.security_learning.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.util.logging.Logger;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = Logger.getLogger(SecurityConfiguration.class.getSimpleName());

    private final LoggingFilter loggingFilter;

    public SecurityConfiguration(LoggingFilter loggingFilter) {
        this.loggingFilter = loggingFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .addFilterBefore((request, response, chain) -> {
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    LOGGER.info(username + ":" + password);
                    chain.doFilter(request, response);
                }, BasicAuthenticationFilter.class)
                .addFilterAfter(loggingFilter, BasicAuthenticationFilter.class)
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
