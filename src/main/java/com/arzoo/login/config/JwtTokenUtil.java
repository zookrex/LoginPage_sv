package com.arzoo.login.config;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
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
	private  String salt;
	
	@Value("${jwt.exTime}")
	private Long expiration;
	
	 public String generateToken(String username, String role) throws NoSuchAlgorithmException {
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("role", role);
	        return createToken(claims, username);
	    }
	 
	 private  KeyPair generateKeyPair() throws NoSuchAlgorithmException {
		 
	        KeyPairGenerator generator = KeyPairGenerator.getInstance("EC");
	        generator.initialize(256);
	        return generator.generateKeyPair();
	    }
	
	public String createToken(Map<String, Object> claims, String username) throws NoSuchAlgorithmException {
		 KeyPair keyPair = generateKeyPair();
	        PrivateKey privateKey = keyPair.getPrivate();
		
		String result= Jwts.builder()
						.setClaims(claims)
						.setSubject(username)
						.setIssuedAt(new Date(System.currentTimeMillis()))
						.setExpiration(new Date(System.currentTimeMillis()+expiration))
						.signWith(privateKey, SignatureAlgorithm.ES256)
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
