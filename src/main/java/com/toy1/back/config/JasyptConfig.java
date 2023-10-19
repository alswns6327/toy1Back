package com.toy1.back.config;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEncryptableProperties
public class JasyptConfig {

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor(){

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();

        config.setPassword("testKey");
        config.setAlgorithm("PBEWITHMD5ANDDES");
        config.setKeyObtentionIterations("1000");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setPoolSize("1");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

}
