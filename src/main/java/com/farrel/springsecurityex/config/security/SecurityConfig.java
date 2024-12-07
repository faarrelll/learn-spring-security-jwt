package com.farrel.springsecurityex.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // anotasi untuk memberi tahu spring bahwa ini adalah config
@EnableWebSecurity // memberi tahu spring agar menjaalankan bean dibawah (security filter chain) untuk dijalankan sebagai alur security, dan tidak menggunakan spring security defaultnya.
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable()) // disable csrf
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/register").permitAll()
                        .anyRequest().authenticated()) // agar ada pelingdung dalam setiap endpoint, jadi hanya yang sudah terautentikasi yang bisa akses
                .httpBasic(Customizer.withDefaults()) // agar saat di get di postman tidak mengembalikan response form login bawaan spring
                .formLogin(Customizer.withDefaults()) // mengaktifkan form login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // membuat http menjadi stateless, jadi tidak perlu memikirkan session id, itulah mengapa kita disable csrf
                .build();

    }


    //5. buat bean authenticationProvider untuk providernya
    // bisa pilih sesuai keinginan
    //fyi : jangan satuin bean password encoder dengan authenticationprovider nanti error
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder); // ngasih tau kalo passwordnya diencode sama bean passwordEncoder
        authenticationProvider.setUserDetailsService(userDetailsService); // userDetailService disini perlu di buat class implementnya dulu, diproject ini kan yang implement UserAccountService
        return authenticationProvider;
    }


}
