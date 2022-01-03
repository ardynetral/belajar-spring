package com.belajar.movies.belajarspring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

  private JwtTokenProvider jwtTokenProvider;

  public JwtTokenFilterConfigurer(JwtTokenProvider jwtTokenProvider) {
    log.info("Masuk JwtTokenFilterConfig atas");
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    log.info("Masuk JwtTokenFilterConfig bawah");
    JwtTokenFilter customFilter = new JwtTokenFilter(jwtTokenProvider);
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
  }

}
