package com.vg.sct.common.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.vg.sct.common.support.exception.AuthException;

import java.text.ParseException;

/**
 * @description: numbus-jose-jwt工具
 * @author: xieweij
 * @create: 2021-02-25 16:08
 **/
public class NjJwtUtils {

    public static String verifyTokenByRSA(String token, RSAKey rsaKey) throws ParseException, JOSEException {
        //从token中解析JWS对象
        JWSObject jwsObject = JWSObject.parse(token);
        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        //使用RSA公钥创建RSA验证器
        JWSVerifier jwsVerifier = new RSASSAVerifier(publicRsaKey);
        if (!jwsObject.verify(jwsVerifier)) {
            throw new AuthException("token签名不合法！");
        }
        String payload = jwsObject.getPayload().toString();

        return payload;
    }

    public static void main(String[] args) throws ParseException {
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJsZW5vIiwic2NvcGUiOlsiYWxsIl0sIm5pY2tfbmFtZSI6IuafoOaqrOeyviIsImV4cCI6MTYxNDE2MDU0OCwianRpIjoiNDM3YzdkMzAtNmIyOS00MmU3LWFkZjMtZjAyMDgwZTVmMmUxIiwiY2xpZW50X2lkIjoiU0NUX1dFQiJ9.XwQ30AHLqXBksDO6DuTI2gW7s5cJcu6kh_bhbopaJ7fx0GokIM4useJm7x_RlUmURLQdWUtSatsAeIGwyiXQRKI56raOx9NZt2kOmpiF70W8BhhK-3HbxM63oaAxKGvfQpw5SX3ntO2wqVXLiSHsV5YDaOgW_Ibeef9NUKzJDE6ac8vOaqmA4VExNJu25Pn4r4oC3lbi4ovhk43RH26hUKIMgaSj-F5L9m6EDCAFCi-ImzKRTbuEQRPFEYCE5w7t9hTJ9y1VVrYOHuUGnR7Nqsrcz4Gl_5lwOH3SeYFinDg6nsgr080stsXPkdQEiIzE6VCnY-dvivNiZ0oFcqTJWg";

        JWSObject jwsObject = JWSObject.parse(token);

        System.out.println(jwsObject.getPayload().toString());
    }
}
