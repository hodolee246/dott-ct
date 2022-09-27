package com.example.dottct.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_URL = "/logout";
    private static final String H2_CONSOLE = "/h2-console/**";
    private static final String[] PATHS = {"/", "/signup", LOGIN_URL, LOGOUT_URL, H2_CONSOLE};
    private static final String USERNAME_PARAMETER = "username";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authRequest -> authRequest.antMatchers(PATHS).permitAll())
                .formLogin()
                .loginProcessingUrl(LOGIN_URL)
                .usernameParameter(USERNAME_PARAMETER)
                .and()
                .logout()
                .logoutUrl(LOGOUT_URL)
                .and()
                .csrf().disable();

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers(H2_CONSOLE);
    }

}
