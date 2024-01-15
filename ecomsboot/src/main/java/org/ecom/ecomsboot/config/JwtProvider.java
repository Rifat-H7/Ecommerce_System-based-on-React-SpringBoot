package org.ecom.ecomsboot.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {
    public String generateToken(Authentication auth){
        String jwt= Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+846000000))
                .claim("email",auth.getName())
                .signWith(Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes())).compact();
        return jwt;
    }
    public String getEmailFromJwt(String jwt){
        jwt=jwt.substring(7);
        String email=Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes())).build().parseClaimsJws(jwt).getBody().get("email").toString();
        return email;
    }
}
