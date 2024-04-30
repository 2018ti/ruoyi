package com.xiaoyagao.common.utils;


import io.jsonwebtoken.*;

import javax.xml.crypto.Data;
import java.util.Date;

public class TokenUtils {
    private static final  long  EXPIRE_TIME = 1000*60;//一小时
    private static final  String SECRET_KEY = "xiaoyagao";//密钥，

    public static String generateToken(String userId,String username,String password){
        String token = Jwts.builder().
                setSubject(userId)
                .setExpiration(new Date(new Date().getTime()+EXPIRE_TIME))
                .claim("username",username)
                .claim("password",password)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
        return token;

    }

    public static Claims getClaimByToken(String token){

        Claims claims = null ;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
           return Jwts.claims().setSubject("expired");
        } catch (SignatureException e){
            return Jwts.claims().setSubject("invalid");
        }
        return claims;
    }

    //校验token是否合法以及过期
    public static int parseToken(String token){
        Claims claims = getClaimByToken(token);
        if(claims.getSubject().equals("expired")){
            return -1;
        }
        if(claims.getSubject().equals("invalid")){
            return -2;
        }
       //token合法
        return 1;
    }


}
