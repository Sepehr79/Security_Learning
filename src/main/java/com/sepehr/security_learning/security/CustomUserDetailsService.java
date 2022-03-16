package com.sepehr.security_learning.security;

import lombok.SneakyThrows;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final List<UserDetails> users;

    public CustomUserDetailsService(PasswordEncoder passwordEncoder, List<User> users) {
        this.passwordEncoder = passwordEncoder;
        this.users = List.of(
                User.withUsername("sepehr79").password(passwordEncoder.encode("1234")).authorities("ADMIN").build(),
                User.withUsername("amir").password(passwordEncoder.encode("123456789")).authorities("EMP").build(),
                User.withUsername("ali").password(passwordEncoder.encode("12345678910")).authorities("READ").build()
        );
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream().filter(userDetails -> userDetails.getUsername().equals(username)).findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
