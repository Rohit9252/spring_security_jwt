package com.example.springjwt.jwt;

import com.example.springjwt.Service.UserDetailImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    //@Value("${jwtapp.app.jwtSecret}")
    private  final String jwtSecret = "secret";

    //@Value("${jwtapp.app.jwtExpirationMs}")

    private  final int jwtExpirationInMs = 86400000;

    public String createtoken(Authentication authentication) {
         UserDetailImpl user = (UserDetailImpl) authentication.getPrincipal();
      String token =   Jwts.builder()
                .setSubject(user.getUsername())
            .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+jwtExpirationInMs ))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();


        return token;
    }


    public boolean verifyToken(String token) {

        try{
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }
        catch (SignatureException e){
            throw new SignatureException("Invalid JWT signature");
        }
        catch (MalformedJwtException e){
            throw new MalformedJwtException("Invalid JWT token");
        }
        catch(UnsupportedJwtException e){
            throw new UnsupportedJwtException("Unsupported JWT token");
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("JWT claims string is empty");
        }

    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().
                setSigningKey(jwtSecret).
                parseClaimsJws(token).
                getBody().getSubject();
    }



}
