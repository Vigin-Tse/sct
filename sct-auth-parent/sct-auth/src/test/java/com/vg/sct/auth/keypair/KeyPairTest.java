package com.vg.sct.auth.keypair;

import cn.hutool.core.codec.Base64;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.vg.sct.common.utils.NjJwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

/**
 * @description: 获取证书秘钥测试
 * @author: xieweij
 * @create: 2021-02-24 15:49
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KeyPairTest {

    @Test
    public void getKeyPairTset(){

        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt/jwt.jks"), "scarf123".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "scarf123".toCharArray());
        PrivateKey privateKey = keyPair.getPrivate();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        RSAKey key = new RSAKey.Builder(publicKey).build();
//        Map<String, Object> map = JWKSet(key).toJSONObject();

    }


    @Test
    public void showKeys(){
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt/jwt.jks"), "scarf123".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "scarf123".toCharArray());
        PrivateKey privateKey = keyPair.getPrivate();

        RsaSigner signer = new RsaSigner((RSAPrivateKey) privateKey);
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RsaVerifier verifier = new RsaVerifier(publicKey);
        String verifierKey = "-----BEGIN PUBLIC KEY-----\n" + new String(Base64.encode(publicKey.getEncoded()))
                + "\n-----END PUBLIC KEY-----";

        System.out.println(verifierKey);
    }

    @Test
    public void verifyTokenByRSA() throws ParseException, JOSEException {
        String token ="eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoxLCJ1c2VyX25hbWUiOiJsZW5vIiwic2NvcGUiOlsiYWxsIl0sIm5pY2tfbmFtZSI6IuafoOaqrOeyviIsImV4cCI6MTYxNDE2MDU0OCwianRpIjoiNDM3YzdkMzAtNmIyOS00MmU3LWFkZjMtZjAyMDgwZTVmMmUxIiwiY2xpZW50X2lkIjoiU0NUX1dFQiJ9.XwQ30AHLqXBksDO6DuTI2gW7s5cJcu6kh_bhbopaJ7fx0GokIM4useJm7x_RlUmURLQdWUtSatsAeIGwyiXQRKI56raOx9NZt2kOmpiF70W8BhhK-3HbxM63oaAxKGvfQpw5SX3ntO2wqVXLiSHsV5YDaOgW_Ibeef9NUKzJDE6ac8vOaqmA4VExNJu25Pn4r4oC3lbi4ovhk43RH26hUKIMgaSj-F5L9m6EDCAFCi-ImzKRTbuEQRPFEYCE5w7t9hTJ9y1VVrYOHuUGnR7Nqsrcz4Gl_5lwOH3SeYFinDg6nsgr080stsXPkdQEiIzE6VCnY-dvivNiZ0oFcqTJWg";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt/jwt.jks"), "scarf123".toCharArray());
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt", "scarf123".toCharArray());
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        PrivateKey privateKey = keyPair.getPrivate();
//        RSAKey raskey = new RSAKey.Builder(publicKey).build();
        NjJwtUtils.verifyTokenByRSA(token, publicKey);
    }
}
