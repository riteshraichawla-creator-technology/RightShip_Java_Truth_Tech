package com.java.truth.softwares.rightshipsoftfunctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;

@SpringBootApplication(exclude = {GatewayAutoConfiguration.class})
public class RightShipJavaTruthSoftwareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RightShipJavaTruthSoftwareApplication.class, args);
    }

}
