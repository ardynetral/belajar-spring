package com.belajar.movies.belajarspring.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final JwtTokenProvider jwtTokenProvider;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    log.info("Masuk WebSecurity");

    // Disable CSRF (cross site request forgery)
    http.csrf().disable();

    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
        .antMatchers("/api/v1/auth/register").permitAll()
            .antMatchers("/api/v1/auth/login").permitAll()
            .antMatchers("/api/v1/backoffice/movies/schedule").permitAll()
            .antMatchers("/api/v1/backoffice/tags").permitAll()
            .antMatchers("/api/v1/movies/**").permitAll()
            .antMatchers("/api/v1/rajaongkir/**").permitAll()
        // Disallow everything else..
        .anyRequest().authenticated();

    // If a user try to access a resource without having enough permissions
    http.exceptionHandling().accessDeniedPage("/login");

    // Apply JWT
    http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

    // Optional, if you want to test the API from a browser
    // http.httpBasic();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }


}
