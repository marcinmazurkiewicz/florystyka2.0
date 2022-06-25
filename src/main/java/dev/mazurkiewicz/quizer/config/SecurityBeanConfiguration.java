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
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SecurityBeanConfiguration {

    SecurityConfiguration securityConfiguration;

    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().configurationSource(request -> {
                            CorsConfiguration cors = new CorsConfiguration();
                            cors.setAllowedOrigins(securityConfiguration.allowedOrigins());
                            cors.setAllowedMethods(securityConfiguration.allowedMethods());
                            cors.setAllowedHeaders(securityConfiguration.allowedHeaders());
                            cors.setExposedHeaders(securityConfiguration.exposedHeaders());
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
