package com.morphiq.morphiq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Regular user - can only chat
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("Codex")
                .roles("USER")
                .build();
                
        // Admin user - can chat and train models
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("Codex")
                .roles("ADMIN")
                .build();
                
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/", "/index.html", "/login.html", "/static/**").permitAll()
                .requestMatchers("/api/chat/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/models/train", "/api/models/behavior/**").hasRole("ADMIN")
                .requestMatchers("/api/models/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login.html")
                .permitAll()
            )
            .csrf(csrf -> csrf.disable()); // Disable CSRF for simplicity in development
        return http.build();
    }
}