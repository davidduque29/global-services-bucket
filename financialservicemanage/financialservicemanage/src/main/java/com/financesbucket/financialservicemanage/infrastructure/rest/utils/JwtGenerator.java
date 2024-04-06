package com.financesbucket.financialservicemanage.infrastructure.rest.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JwtGenerator {

    private static final int KEY_SIZE_BYTES = 32; // Tamaño de la clave en bytes
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    // Genera una clave secreta utilizando SecureRandom
    private static final Key SECRET_KEY = Keys.hmacShaKeyFor(generateSecureRandomBytes(KEY_SIZE_BYTES));

    public static String generateToken(String subject, long expirationTimeMillis) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationTimeMillis);

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static byte[] generateSecureRandomBytes(int length) {
        byte[] bytes = new byte[length];
        SECURE_RANDOM.nextBytes(bytes);
        return bytes;
    }
}