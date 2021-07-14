package com.coupang.marketplace.fixture;

import com.coupang.marketplace.domain.User;

public class UserFixture {

    public static class User1 {
        public static final Long ID = 1L;
        public static final String NAME = "user1";
        public static final String EMAIL = "user1@test.com";
        public static final String PASSWORD = "1111";
        public static final String ENCRYPTED_PASSWORD = "pw1####";
        public static final String SALT = "salt1####";
        public static final String PHONE = "01011111111";

        public static final User USER = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCRYPTED_PASSWORD)
                .salt(SALT)
                .phone(PHONE)
                .build();
    }

    public static class User2 {
        public static final Long ID = 2L;
        public static final String NAME = "user2";
        public static final String EMAIL = "user2@test.com";
        public static final String PASSWORD = "2222";
        public static final String ENCRYPTED_PASSWORD = "pw2####";
        public static final String SALT = "salt2####";
        public static final String PHONE = "01022222222";

        public static final User USER = User.builder()
                .id(ID)
                .name(NAME)
                .email(EMAIL)
                .password(ENCRYPTED_PASSWORD)
                .salt(SALT)
                .phone(PHONE)
                .build();
    }
}