package com.coupang.marketplace.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class User {

    private Long id;
    private String name;
    private String email;
    private String salt;
    private String password;
    private String phone;

    @Builder
    public User(Long id, String name, String email, String salt, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salt = salt;
        this.password = password;
        this.phone = phone;
    }
}
