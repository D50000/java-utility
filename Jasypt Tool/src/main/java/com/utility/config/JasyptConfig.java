package com.utility.config;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JasyptConfig {

    // Configuration of "jasypt library"
    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword("jasypt-key"); // Same as application.properties DB password.
        return encryptor;
    }
}
