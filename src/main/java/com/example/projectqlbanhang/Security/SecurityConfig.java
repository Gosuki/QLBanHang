package com.example.projectqlbanhang.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final String[] USER_URLs={"/user/**","/cart/**","/bill/**"};
    private static final String[] SECURED_URLs={"/product/**"};
    private static final String[] UN_SECURED_URLs={
            "/product/all",
            "/user/sign-up",
            "/user/verify/{token}"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(UN_SECURED_URLs).permitAll().and()
                .authorizeHttpRequests().requestMatchers(USER_URLs)
                .hasAnyAuthority("ROLE_ADMIN","ROLE_USER").and()
                .authorizeHttpRequests().requestMatchers(SECURED_URLs)
                .hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated().and().httpBasic().and().build();

    }
}
