package com.example.micromatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // Chaîne PUBLIQUE: pas de JWT, autorise CORS et accès libre aux listes
    @Bean
    @Order(1)
    public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/actuator/**", "/matches/clubs", "/matches/clubs/**", "/matches/locals", "/matches/locals/**")
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }

    // Chaîne PROTÉGÉE: tout le reste nécessite JWT
    @Bean
    @Order(2)
    public SecurityFilterChain protectedChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                //.authorizeHttpRequests(auth -> auth
                        //.requestMatchers( "/actuator/**").permitAll()
                        //.anyRequest().authenticated()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**").permitAll()
                )
                //.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
                        .oauth2ResourceServer(oauth2 -> oauth2.disable());
        return http.build();
    }
}