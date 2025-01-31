package fr.ttl.atlasdd.utils.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import fr.ttl.atlasdd.apidto.user.UserLightAuthApiDto;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Value("${JWT_SECRET}")
    private String jwtSecret;

    @Value("${JWT_EXPIRATION}")
    private long jwtExpiration;

    public String generateToken(UserLightAuthApiDto user) {
        String mail = user.getEmail();
        logger.info("Generating JWT Token for email: {}", mail);

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", mail);
        logger.info("JWT Claims: {}", claims);

        String token = Jwts.builder()
                .setSubject(mail)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(io.jsonwebtoken.security.Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();

        logger.info("JWT Token Subject: {}", mail);
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
            logger.info("JWT Token Body: {}", claims);
            logger.info("JWT Token Email: {}", email);

            return email;
        } catch (Exception e) {
            logger.error("Error extracting email from JWT Token", e);
            return null;
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