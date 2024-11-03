package com.example.company.auth;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
//@EnableWebSecurity
@AllArgsConstructor
public class AuthConfig {
//    private final KeycloakLogoutHandler keycloakLogoutHandler;

//    @Bean
//    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(auth ->
//                auth
//                        .anyRequest()
//                        .authenticated());
//        http.oauth2ResourceServer(oauth2 -> oauth2
//                .jwt(Customizer.withDefaults()));
//        http.oauth2Login(Customizer.withDefaults())
//                .logout(logout -> logout.addLogoutHandler(keycloakLogoutHandler).logoutSuccessUrl("/"));
//        return http.build();
//    }

}
