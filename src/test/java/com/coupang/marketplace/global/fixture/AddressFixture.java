package com.coupang.marketplace.global.fixture;

import com.coupang.marketplace.address.domain.Address;

public class AddressFixture {

    public static class Address1{
        public static final long ID = 1L;
        public static final boolean IS_MAIN = true;
        public static final String NAME = "Home";
        public static final long USER_ID = 1L;
        public static final String CONTENT = "Seoul";

        public static final Address address = Address.builder()
                .id(ID)
                .isMain(IS_MAIN)
                .name(NAME)
                .userId(USER_ID)
                .content(CONTENT)
                .build();
    }
}
