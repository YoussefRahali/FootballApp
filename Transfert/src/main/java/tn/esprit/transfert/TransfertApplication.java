package tn.esprit.transfert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200")
@EnableFeignClients
@EnableDiscoveryClient
public class TransfertApplication {

    public static void main(String[] args) {
        SpringApplication.run ( TransfertApplication.class, args );
    }

}
