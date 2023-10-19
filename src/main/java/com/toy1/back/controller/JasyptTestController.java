package com.toy1.back.controller;

import lombok.RequiredArgsConstructor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class JasyptTestController {

    @Value("${jasypt.Password}")
    private String pw;

    @GetMapping("/aaa")
    public ResponseEntity<String> a (){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("testKey");
        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setPoolSize("1");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);

        System.out.println(encryptor.encrypt("vnvnvn125a"));
        System.out.println("------------------------------------------aaa");
//        System.out.println(url);
//        System.out.println(id);
        System.out.println(pw);
        System.out.println("------------------------------------------aaa");
        return ResponseEntity.ok(pw);
    }

}
