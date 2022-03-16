package com.sepehr.security_learning.security.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PasswordEncoderType {

    BCRYPT("bcrypt"), PBKDF2("pbkdf2"),
    SCRYPT("scrypt");

    private final String value;
}
