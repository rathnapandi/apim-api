package com.axway.apimcassandra;

import com.datastax.oss.driver.api.core.CqlIdentifier;

public class Util {

    public static String removeQuote(String value){
        if(value != null)
            return CqlIdentifier.fromCql(value).asInternal();
        return null;
    }
}
