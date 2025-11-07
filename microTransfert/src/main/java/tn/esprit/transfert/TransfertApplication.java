package tn.esprit.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TransfertApplication {

    public static void main(String[] args) {
        SpringApplication.run ( TransfertApplication.class, args );
    }




}
