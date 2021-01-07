package com.vg.sct.common.utils;

import com.vg.sct.common.domain.bo.CurrentUserDetails;
import com.vg.sct.common.exception.AuthException;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @description: Jwt token工具类
 * @author: xieweij
 * @create: 2021-01-07 14:53
 **/
public class JwtUtils {

    //秘钥
    private static final String SECRET_KEY = "scart_key_2020";

    //设定过期时间为5min
    private static long EXP_TIME =  5 * 60 * 1000;

    /**
     * 生成用户token
     * @param user  用户信息
     * @return
     */
    public static String createToken(CurrentUserDetails user){

        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //创建payload的私有声明（根据特定的业务需要添加，可被解密部分，不允许放任敏感信息）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId" , user.getId());
        claims.put("userName", user.getUserName());

        //签发人
        String issuer = "scart_" + user.getUserName();

        //签发时间
        Date issuedAt = new Date();

        //过期时间
        long expDateTime = issuedAt.getTime() + EXP_TIME;
        Date expDate = new Date(expDateTime);

        JwtBuilder builder = Jwts.builder()             // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)                      // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(UUID.randomUUID().toString())    // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(new Date())                // iat: jwt的签发时间
                .setIssuer(issuer)                      // issuer：jwt签发人
//                .setSubject(subject)                  // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .setExpiration(expDate)
                .signWith(signatureAlgorithm, SECRET_KEY); // 设置签名使用的签名算法和签名使用的秘钥

        return builder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){

        try {
            Claims  claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (ExpiredJwtException  eje){
            throw new AuthException("token已失效");
        }catch (Exception e){
            throw new AuthException("不合法token");
        }
    }

    public static void main(String[] args){
        CurrentUserDetails user = new CurrentUserDetails();
        user.setId(1);
        user.setUserName("vicky");

//        String token = JwtUtils.createToken(user);

//        System.out.println(token);

        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzY2FydF92aWNreSIsInVzZXJOYW1lIjoidmlja3kiLCJleHAiOjE2MTAwMDkwNDIsInVzZXJJZCI6MSwiaWF0IjoxNjEwMDA4NzQzLCJqdGkiOiIwMjE3ODUxMS0xOTcxLTRlNTAtYjFmNS05OWEyZGQ4MmM1NDcifQ.2WgqXtdsEQDeaq2ziklZ-AioWpJEk6FUsxu-PordHoI";
        Claims claims = JwtUtils.parseToken(token);

        System.out.println(claims.get("userId"));
        System.out.println(claims.get("userName"));
    }
}
