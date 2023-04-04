package com.spring.backend.easyvet.security;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.spring.backend.easyvet.exception.JwtExceptionHandler;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	@Value("${app.jwt-secret}")
	private String jwtSecret;

	@Value("${app.jwt-expiration-milliseconds}")
	private String jwtExpirationInMs;
	
	
	public String generateAccessToken(Authentication authentication) {
		String email = authentication.getName();
		
		long jwtExpirationInMs = Long.parseLong(this.jwtExpirationInMs);
		Instant actualDate = Instant.now();
		Instant expirationDate = actualDate.plusMillis(jwtExpirationInMs);
		
		final String authorities = authentication.getAuthorities()
	            .stream()
	            .map(GrantedAuthority::getAuthority)
	            .collect(Collectors.joining(","));
	        
	        final Map<String, Object> claims =  new HashMap<>();
	        claims.put("Authorities", authorities);
		
		return Jwts.builder()
				.setIssuedAt(Date.from(Instant.now()))
				.setClaims(claims)
				.setSubject(email)
				.setExpiration(Date.from(expirationDate))
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}
	
	public String getEmailFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		
		if(claims.getExpiration().before(Date.from(Instant.now()))) {
			throw new JwtException("Token is expired");
		}
		return claims.getSubject();
	}
	
	public boolean isValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			throw new JwtExceptionHandler(HttpStatus.BAD_REQUEST, "Sign JWT is not valid");
		} catch (MalformedJwtException ex) {
			throw new JwtExceptionHandler(HttpStatus.BAD_REQUEST, "Token JWT is not valid");
		} catch (ExpiredJwtException ex) {
			throw new JwtExceptionHandler(HttpStatus.BAD_REQUEST, "Token JWT is expired");
		} catch (UnsupportedJwtException ex) {
			throw new JwtExceptionHandler(HttpStatus.BAD_REQUEST, "Token JWT is not compatible");
		} catch (IllegalArgumentException ex) {
			throw new JwtExceptionHandler(HttpStatus.BAD_REQUEST, "The claims JWT is empty");
		}
	}
	
	
}
