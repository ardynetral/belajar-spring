package com.belajar.movies.belajarspring.security;

import com.belajar.movies.belajarspring.datasource.AppUserRole;
import com.belajar.movies.belajarspring.util.ErrorCode;
import com.belajar.movies.belajarspring.util.exception.ValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

  /**
   * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
   * microservices environment, this key would be kept on a config-server.
   */
  @Value("${security.jwt.token.secret-key:secret-key}")
  private String secretKey;

  @Value("${security.jwt.token.expire-length:3600000}")
  private long validityInMilliseconds = 3600000; // 1h

  @Autowired
  private MyUserDetails myUserDetails;

  @PostConstruct
  protected void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
  }

  public String createToken(String username, List<AppUserRole> appUserRoles) {
    log.info("Create Token in Token Provider");
    Claims claims = Jwts.claims().setSubject(username);
    claims.put("auth", appUserRoles.stream().map(s -> new SimpleGrantedAuthority(s.getAuthority())).filter(Objects::nonNull).collect(Collectors.toList()));

    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()//
        .setClaims(claims)//
        .setIssuedAt(now)//
        .setExpiration(validity)//
        .signWith(SignatureAlgorithm.HS256, secretKey)//
        .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = myUserDetails.loadUserByUsername(getUsername(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUsername(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  public String resolveToken(HttpServletRequest req) {
    String bearerToken = req.getHeader("Authorization");
    if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }

  public boolean validateToken(String token) throws ValidationException {
    log.info("Validate Token in Token Provider");
    try {
//      System.out.println("token validasi "+token);
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (JwtException | IllegalArgumentException e) {
//      e.printStackTrace();
      Map map = new HashMap();
      map.put("error", "Expired or invalid JWT token");
      throw new ValidationException(ErrorCode.ERROR.INTERNAL_SERVER_ERROR.name(), map);
    }
  }

}
