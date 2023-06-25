package com.arzoo.login.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.arzoo.login.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
	
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.exTime}")
	private Long expiration;
	
	public String generateToken(UserDetails  user) {
		
		Map<String, Object> claims =new HashMap<>();
		String result= Jwts.builder()
						.setClaims(claims)
						.setSubject(user.getUsername())
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis()+expiration))
						.signWith(SignatureAlgorithm.ES512,salt)
						.compact();
				;
		
		return result;
	}
	
	public Boolean validateToken(String token, User user) {
		final String username=extractUsername(token);
		return (username.equals(user.getUsername()) && !isTokenExpired(token));
	}
	
	private boolean isTokenExpired(String token) {
		final Date expiration = extractExpirationDate(token); 
		return expiration.before(new Date());
	}

	public Date extractExpirationDate(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		
		final Claims claims= extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		
		return Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		
		return extractClaim(token,Claims::getSubject);
	}

}
