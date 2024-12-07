package com.farrel.springsecurityex.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // anotasi untuk memberi tahu spring bahwa ini adalah config
@EnableWebSecurity // memberi tahu spring agar menjaalankan bean dibawah (security filter chain) untuk dijalankan sebagai alur security, dan tidak menggunakan spring security defaultnya.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable()) // disable csrf
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()) // agar ada pelingdung dalam setiap endpoint, jadi hanya yang sudah terautentikasi yang bisa akses
                .httpBasic(Customizer.withDefaults()) // agar saat di get di postman tidak mengembalikan response form login bawaan spring
                .formLogin(Customizer.withDefaults()) // mengaktifkan form login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // membuat http menjadi stateless, jadi tidak perlu memikirkan session id, itulah mengapa kita disable csrf
                .build();

    }
}
