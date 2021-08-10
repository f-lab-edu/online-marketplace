package com.coupang.marketplace;

import static com.coupang.marketplace.global.constant.Timezone.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class MarketplaceApplication {

    @PostConstruct
    public void setUp(){
        TimeZone.setDefault(TimeZone.getTimeZone(LocalTimezone));
    }

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceApplication.class, args);
    }

}