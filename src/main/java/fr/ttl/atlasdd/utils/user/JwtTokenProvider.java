package fr.ttl.atlasdd.utils.user;

import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION}")
    private long jwtExpiration;

    public String generateToken(UserLightAuthApiDto user) {
        String mail = user.getEmail();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", mail);

        String token = Jwts.builder()
                .setSubject(mail)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();

        return token;
    }

    public String generatePasswordResetToken(String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("type", "password-reset");

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();

        return token;

    }

    public String getMailFromToken(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.get("email", String.class);

            return email;
        } catch (Exception e) {
            throw new RuntimeException("JWT Token parsing failed");
        }
    }

    public String getType(String token) {
        try {
            var claims = Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String type = claims.get("type", String.class);

            return type;
        } catch (Exception e) {
            throw new RuntimeException("JWT Token parsing failed");
        }
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}