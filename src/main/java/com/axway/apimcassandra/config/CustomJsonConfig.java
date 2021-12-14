package com.axway.apimcassandra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
//@EnableWebMvc
public class CustomJsonConfig{

}
//public class CustomJsonConfig implements WebMvcConfigurer {
//
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
//        converters.add( jsonConverter());
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter jsonConverter() {
//
//
//        ObjectMapper builder = new Jackson2ObjectMapperBuilder().deserializers(
//                new StringQuoteDeserializer())
//            .build();;
////        builder.serializationInclusion(JsonInclud.Include.NON_NULL);
////        builder.timeZone(TimeZone.getTimeZone(timeZone));
//
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(
//           builder
//        );
//
//        return jsonConverter;
//    }
//}
//public class CustomJsonConfig implements WebMvcConfigurer {
//
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
//        converters.add( jsonConverter());
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter jsonConverter() {
//
//
//        ObjectMapper builder = new Jackson2ObjectMapperBuilder().deserializers(
//                new StringQuoteDeserializer())
//            .build();;
////        builder.serializationInclusion(JsonInclud.Include.NON_NULL);
////        builder.timeZone(TimeZone.getTimeZone(timeZone));
//
//        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(
//           builder
//        );
//
//        return jsonConverter;
//    }
//}
