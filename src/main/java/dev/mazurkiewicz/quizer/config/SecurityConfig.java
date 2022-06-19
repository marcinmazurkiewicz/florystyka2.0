package dev.mazurkiewicz.quizer.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

    SecurityProperties properties;

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> {
                            CorsConfiguration cors = new CorsConfiguration();
                            cors.setAllowedOrigins(properties.allowedOrigins());
                            cors.setAllowedMethods(properties.allowedMethods());
                            cors.setAllowedHeaders(properties.allowedHeaders());
                            cors.setExposedHeaders(properties.exposedHeaders());
                    cors.setAllowCredentials(true);
                            return cors;
                        }
                )
                .and()
                .authorizeRequests()
                .anyRequest()
                .permitAll();
        return http.build();
    }
}
