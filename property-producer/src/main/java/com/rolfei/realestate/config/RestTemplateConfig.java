package com.rolfei.realestate.config;

import com.fasterxml.jackson.core.JsonGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {



    @Bean
    public Jackson2ObjectMapperBuilder Jackson2JsonObjectMapper() {
        org.springframework.http.converter.json.Jackson2ObjectMapperBuilder b = new org.springframework.http.converter.json.Jackson2ObjectMapperBuilder();
        b.indentOutput(true);
        b.featuresToEnable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        return b;
    }

    @Bean
    public RestTemplate restTemplate() {return new RestTemplate();}
}
