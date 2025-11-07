package com.example.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        //return http
          //      .csrf(ServerHttpSecurity.CsrfSpec::disable)
            //    .authorizeExchange(ex -> ex
              //          .pathMatchers("/eureka/**").permitAll()
                //        .anyExchange().authenticated()
              //  )
               // .oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()))
                //.build();
        // return http
          //      .csrf(ServerHttpSecurity.CsrfSpec::disable)
            //    .authorizeExchange(ex -> ex
              //          .pathMatchers("/actuator/**", "/eureka/**").permitAll()
                //        .anyExchange().permitAll()
              //  )
                //.oauth2ResourceServer(oauth -> oauth.jwt())  // <- disable for now
              //  .build();

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable) // Désactive CSRF
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/eureka/**").permitAll() // routes publiques
                                .anyExchange().authenticated() // toutes les autres routesprotégées
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()) // active JWT
                )
                .build();
    }
    // Décodeur JWT basé sur la JWK Set URL de Keycloak
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        String jwkSetUri =
                "http://localhost:8085/realms/Football/protocol/openid-connect/certs";
        return NimbusReactiveJwtDecoder.withJwkSetUri(jwkSetUri).build();
    }
}

