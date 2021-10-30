package dev.mazurkiewicz.florystyka.config;

import com.google.common.collect.Lists;
import dev.mazurkiewicz.florystyka.jwt.JwtProperties;
import dev.mazurkiewicz.florystyka.recaptcha.RecaptchaProperties;
import dev.mazurkiewicz.florystyka.user.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true)
@EnableConfigurationProperties({JwtProperties.class, SecurityProperties.class, RecaptchaProperties.class, ApplicationProperties.class})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityProperties securityProperties;
    private final OncePerRequestFilter jwtTokenFilter;
    private final UserService userService;
    private final String metricsBaseEndpoint;

    public SecurityConfig(UserService userService,
                          SecurityProperties securityProperties,
                          @Qualifier("jwtTokenFilter") OncePerRequestFilter jwtTokenFilter,
                          @Value("${management.endpoints.web.base-path}") String metricsBaseEndpoint) {

        this.userService = userService;
        this.jwtTokenFilter = jwtTokenFilter;
        this.securityProperties = securityProperties;
        this.metricsBaseEndpoint = metricsBaseEndpoint;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().configurationSource(request -> getCorsConfiguration());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/v3/questions/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v3/solutions/**").permitAll()
                .antMatchers(HttpMethod.GET, "/resources/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v3/auth/login").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v3/auth/refresh").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v3/users").permitAll()
                .antMatchers(String.format("%s/**", metricsBaseEndpoint)).hasIpAddress(securityProperties.getPrometheusIPAddress())
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration cors = new CorsConfiguration();
        cors.setAllowCredentials(true);
        cors.setAllowedOrigins(securityProperties.getAllowedOrigins());
        cors.setAllowedMethods(Lists.newArrayList("*"));
        cors.setAllowedHeaders(Lists.newArrayList("*"));
        cors.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization",
                "x-xsrf-token", "Access-Control-Allow-Headers", "Origin", "Accept", "X-Requested-With",
                "Content-Type", "Access-Control-Request-Method", "Access-Control-Request-Headers",
                "Content-Disposition"));
        return cors;
    }
}
