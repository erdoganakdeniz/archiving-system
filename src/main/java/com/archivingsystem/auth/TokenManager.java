package com.archivingsystem.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManager {

    private final String key;
    private final int expireTime ;

    @Autowired
    public TokenManager(@Value("${jwt.secret.key : verySecretKey}") String key,
                        @Value("#{${token.expire.minute : 5} * 60 * 1000}") int expireTime) {
        this.key = key;
        this.expireTime=expireTime;
    }
    public String generateToken(String username) {
        long timeMillis = System.currentTimeMillis();
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(timeMillis))
                .setExpiration(new Date(timeMillis + expireTime))
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
        return token;
    }


    public String getUserFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
        
    }

    private Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean hasTokenValid(String token) {
        if (getUserFromToken(token) != null && hasTokenExpire(token)) {
            return true;
        }
        return false;
    }

    private boolean hasTokenExpire(String token) {
        Claims claims = parseToken(token);
        Date now = new Date(System.currentTimeMillis());
        return claims.getExpiration().after(now);
    }
}
