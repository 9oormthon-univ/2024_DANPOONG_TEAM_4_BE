package com.univ.sohwakhaeng.global.config;

import com.siot.IamportRestClient.IamportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${iamport.apikey}")
    private String iamPortApiKey;

    @Value("${iamport.secretkey}")
    private String iamPortApiSecretKey;

    @Bean
    public IamportClient iamportClient() {
        return new IamportClient(iamPortApiKey, iamPortApiSecretKey);
    }

}
