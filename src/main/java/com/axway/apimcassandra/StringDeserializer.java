package com.axway.apimcassandra;

import com.axway.apimcassandra.model.Organization;
import com.axway.apimcassandra.model.OrganizationEntity;
import com.axway.apimcassandra.model.Summary;
import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class StringDeserializer extends com.fasterxml.jackson.databind.deser.std.StringDeserializer {
    private final Logger logger = LoggerFactory.getLogger(StringDeserializer.class);

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        logger.info("sdc");
        String text = super.deserialize(jsonParser, deserializationContext);
        //clean up value
        return text.trim();
    }


//    @Override
//    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
//        logger.info("sdc");
//        //JsonToken jsonToken = jsonParser.getCurrentToken();
////        System.out.println(jsonParser);
////        if (jsonToken == JsonToken.VALUE_STRING) {
////            String str = jsonParser.getText();
////            System.out.println(str);
////            return str;
////        }
//        return "abc";
//    }



//    @Override
//    protected String _deserialize(String s, DeserializationContext deserializationContext) throws IOException {
//        System.out.println(s);
//        if(s != null && s.startsWith("\""))
//            return CqlIdentifier.fromCql(s).asInternal();
//        return null;
//    }
}
