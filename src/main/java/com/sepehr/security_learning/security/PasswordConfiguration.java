package com.sepehr.security_learning.security;

import com.sepehr.security_learning.security.common.PasswordEncoderType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PasswordConfiguration {

    @Value("${password.encoder.algorithm}")
    PasswordEncoderType passwordEncoderType;

    @Bean
    public PasswordEncoder passwordEncoder(){
        Map<String, PasswordEncoder> passwordEncoderMap = new HashMap<>();
        passwordEncoderMap.put("bcrypt", new BCryptPasswordEncoder());
        passwordEncoderMap.put("scrypt", new SCryptPasswordEncoder());
        passwordEncoderMap.put("pbkdf2", new Pbkdf2PasswordEncoder());

        return new DelegatingPasswordEncoder(passwordEncoderType.getValue(), passwordEncoderMap);
    }

}
