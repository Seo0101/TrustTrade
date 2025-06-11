package org.example.trusttrade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TrustTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrustTradeApplication.class, args);
    }

}
