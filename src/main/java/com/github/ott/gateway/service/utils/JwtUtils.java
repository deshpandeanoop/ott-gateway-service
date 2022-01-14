package com.github.ott.gateway.service.utils;

import com.github.ott.gateway.service.data.db.UserAccount;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    // For simplicity and development purposes, cryptographic hash has been hardcoded. But in production, it must be stored in
    // secured location(To avoid the access of the key, secure location should not be the code repository)
    private String SECRET_KEY = "e7e27e49b3bd784ea4918297a78bb3f7";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserAccount userAccount) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userAccount.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        long fiveMinutesInMilliSecs = 1000 * 60 * 10;

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + fiveMinutesInMilliSecs))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserAccount userAccount) {
        final String username = extractUsername(token);
        return (username.equals(userAccount.getUsername()) && !isTokenExpired(token));
    }
}
