package com.dn.shoptech.utils.jwt;

import com.dn.shoptech.model.User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24*60*60*1000; // 24h

    @Value("${app.jwt.secret}")
    private String SECRET_KEY;

    public String GenerateAccessToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("roles",user.getRoles().toString())
                .setIssuer("CodeJava")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();

    }

    public Boolean validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException ex){
            log.error("Jwt expired {}",ex.getMessage());
        }catch (IllegalArgumentException ex){
            log.error("Token is null, empty or has only whitespace {}" ,ex.getMessage());
        }catch (MalformedJwtException ex){
            log.error("JWT is invalid: {}",ex.getMessage());
        }catch (UnsupportedJwtException ex){
            log.error("JWT is not supported {}",ex.getMessage());
        }catch (SignatureException ex){
            log.error("Signature validation failed {}",ex.getMessage());
        }

        return false;
    }

    public String getSubject(String token){
        return pareClaims(token).getSubject();
    }

    public Claims pareClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

}
