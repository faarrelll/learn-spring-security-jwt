package com.farrel.springsecurityex.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // anotasi untuk memberi tahu spring bahwa ini adalah config
@EnableWebSecurity // memberi tahu spring agar menjaalankan bean dibawah (security filter chain) untuk dijalankan sebagai alur security, dan tidak menggunakan spring security defaultnya.
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable()) // disable csrf
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()) // agar ada pelingdung dalam setiap endpoint, jadi hanya yang sudah terautentikasi yang bisa akses
//                .httpBasic(Customizer.withDefaults()) // agar saat di get di postman tidak mengembalikan response form login bawaan spring
//                .formLogin(Customizer.withDefaults()) // mengaktifkan form login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // membuat http menjadi stateless, jadi tidak perlu memikirkan session id, itulah mengapa kita disable csrf
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }



    //5. buat bean authenticationProvider untuk providernya
    // bisa pilih sesuai keinginan
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder); // ngasih tau kalo passwordnya diencode sama bean passwordEncoder
        authenticationProvider.setUserDetailsService(userDetailsService); // userDetailService disini perlu di buat class implementnya dulu, diproject ini kan yang implement UserAccountService
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}
