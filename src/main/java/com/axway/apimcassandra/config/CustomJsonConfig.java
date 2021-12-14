package com.axway.apimcassandra.config;

import com.axway.apimcassandra.StringQuoteDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class CustomJsonConfig {
//    implements
//} WebMvcConfigurer {
//
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        ObjectMapper mapper = new Jackson2ObjectMapperBuilder()
//                .deserializers(
//                       new StringQuoteDeserializer())
//                .build();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(mapper);
//        converters.add(converter);
//    }
}
