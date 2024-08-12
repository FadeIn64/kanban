package ru.fedin.trelorefactor.configurations.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            auth
                    .requestMatchers("/swagger-ui/**")
                    .anonymous()
                    .requestMatchers(HttpMethod.POST,"/users")
                    .anonymous()
                    .requestMatchers(HttpMethod.GET, "/users/**")
                    .authenticated()
                    .anyRequest().permitAll();
        })
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
