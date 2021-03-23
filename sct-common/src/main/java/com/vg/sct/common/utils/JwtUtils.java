package com.vg.sct.common.utils;

import com.vg.sct.common.domain.CurrentUser;
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
    public static String createToken(CurrentUser user){

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
                .signWith(signatureAlgorithm, MD5Utils.encodeMd5(SECRET_KEY)); // 设置签名使用的签名算法和签名使用的秘钥

        return builder.compact();
    }

    /**
     * 解析token
     * @param token
     * @return
     */
    public static Claims parseToken(String token){

        String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApFJNBZMpLkUcOaZHZMpU8VVwV5oFmD+l+gggYDtL5zaYCS11n66KvADM1wlfbzeImafrATj1ixusXAlGg1rlu6VxhZ95R3SEvaFSGJfL3lRhymP6omj0ovfq/Fa8HqCQ0QWWpmsSMNlqTrDd2ZSsdYsjfFzfqxV4s6b5UuVypMxUKMa0ejNhaq6EAPJggxf8GvK/Csy24G0WE5XAQXQ2U4Pp8JHIyDTyNt0K0E9IwG4Bxp0pHjkWG99AlkPBsjgRfazVlk0W90fAbkLdBng0BGAR0daPcYvIyXrIM/6xt+sreyot+KcIBUvAeHmUIQs0S0DSnK9rUzW4jEHsldOIqwIDAQAB\n" +
                "-----END PUBLIC KEY-----";
        try {
            Claims  claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
//                    .setSigningKey(publicKey)
                    .parseClaimsJws(token).getBody();
            return claims;
        }catch (ExpiredJwtException  eje){
            throw new AuthException("token已失效");
        }catch (Exception e){
            throw new AuthException("不合法token");
        }
    }

    /**
     * 返回加密秘钥
     * @return
     */
    public static String getSecretKey(){
        return MD5Utils.encodeMd5(SECRET_KEY);
    }

    public static void main(String[] args){
        CurrentUser user = new CurrentUser();
        user.setId(1);
        user.setUserName("vicky");

//        String token = JwtUtils.createToken(user);

//        System.out.println(token);

        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJsZW5vIiwic2NvcGUiOlsiYWxsIl0sIm5pY2tfbmFtZSI6IuafoOaqrOeyviIsImV4cCI6MTYxNDE2MDU0OCwianRpIjoiNDM3YzdkMzAtNmIyOS00MmU3LWFkZjMtZjAyMDgwZTVmMmUxIiwiY2xpZW50X2lkIjoiU0NUX1dFQiJ9.XwQ30AHLqXBksDO6DuTI2gW7s5cJcu6kh_bhbopaJ7fx0GokIM4useJm7x_RlUmURLQdWUtSatsAeIGwyiXQRKI56raOx9NZt2kOmpiF70W8BhhK-3HbxM63oaAxKGvfQpw5SX3ntO2wqVXLiSHsV5YDaOgW_Ibeef9NUKzJDE6ac8vOaqmA4VExNJu25Pn4r4oC3lbi4ovhk43RH26hUKIMgaSj-F5L9m6EDCAFCi-ImzKRTbuEQRPFEYCE5w7t9hTJ9y1VVrYOHuUGnR7Nqsrcz4Gl_5lwOH3SeYFinDg6nsgr080stsXPkdQEiIzE6VCnY-dvivNiZ0oFcqTJWg";
        Claims claims = JwtUtils.parseToken(token);

        System.out.println(claims.get("userId"));
        System.out.println(claims.get("userName"));
    }
}
