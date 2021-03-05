package com.vg.sct.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @description: 获取RSA公钥接口
 * @author: xieweij
 * @create: 2021-03-05 13:37
 **/
@RestController
@RequestMapping("/rsa")
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public Map<String, Object> getKey(){
        RSAPublicKey publickey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publickey).build();
        return new JWKSet(key).toJSONObject();
    }

}
