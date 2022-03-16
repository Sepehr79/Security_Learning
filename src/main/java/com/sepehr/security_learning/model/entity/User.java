package com.sepehr.security_learning.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String lastName;

    @Column(unique = true)
    private String userName;

    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "USER_AUTHORITIES",
            joinColumns = @JoinColumn(name = "USER", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "AUTHORITY", nullable = false)
    )
    private List<Authority> authorities;

    @Builder(toBuilder = true)
    public User(String name, String lastName, String userName, String password, List<Authority> authorities) {
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public void addAuthority(Authority authority){
        if (authority == null)
            throw new IllegalArgumentException("Null argument");
        if (authorities == null)
            authorities = new ArrayList<>();

        authorities.add(authority);
    }
}
