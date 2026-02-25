package com.example.merchmarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MerchMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MerchMarketApplication.class, args);
    }

}
