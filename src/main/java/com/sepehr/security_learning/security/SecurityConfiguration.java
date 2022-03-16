package com.sepehr.security_learning.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService getUserDetailsService(){
        var userDetailService = new InMemoryUserDetailsManager();
        userDetailService.createUser(User.withUsername("sepehr").password("1234").roles("admin").build());
        userDetailService.createUser(User.withUsername("saba").password("1234").roles("employee").build());
        userDetailService.createUser(User.withUsername("ahmad").password("1234").roles("user").build());
        return userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().logout();

        http.authorizeRequests()
                .mvcMatchers("/admins").hasRole("admin")
                .mvcMatchers("/employees").hasRole("employee")
                .anyRequest().authenticated() // All other requests should be authenticated
                /*anyRequest().permitAll()*/; // No need for authenticate other requests

    }
}
