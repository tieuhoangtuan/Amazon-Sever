package com.tuantieu.amazonserver.security.jwt;

import com.tuantieu.amazonserver.security.userprinciple.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private String jwtSecret = "tieuhoangtuan822001@gmail.com";
    private int jwtExpiration = 86400000;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SecurityException e){
            logger.error("Invalid JWT signature -> Message: {}", e);
        } catch (MalformedJwtException e){
            logger.error("Invalid format Token -> Message: {}", e);
        } catch (ExpiredJwtException e){
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e){
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }

    public String getUserNameFromJwtToken(String token){
        String userName = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token).
                getBody().getSubject();
        return userName;
    }
}
