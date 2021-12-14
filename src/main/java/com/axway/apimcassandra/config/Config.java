package com.axway.apimcassandra.config;

import com.axway.apimcassandra.APIManager;
import com.axway.apimcassandra.StringDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class Config {


//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder().deserializers(new StringDeserializer());
//        return new MappingJackson2HttpMessageConverter(builder.build());
//    }


    @Bean
    public Module stringDeserializer() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new StringDeserializer());
        return module;
    }

    @Bean
    APIManager apiManager(){
        return new APIManager();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI().info(new Info().title("API Manager lightweight API")
                .version(appVersion)
                .description("API Manager lightweight API")
                .termsOfService("https://axway.com")
                .license(new License().name("Apache 2.0")
                        .url("https://axway.com")));
    }
}
