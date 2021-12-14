package com.axway.apimcassandra;

import com.axway.apimcassandra.model.AccessStore;
import com.axway.apimcassandra.model.Application;
import com.axway.apimcassandra.model.FrontendAPI;
import com.axway.apimcassandra.model.Organization;
import com.datastax.oss.driver.api.core.config.DefaultDriverOption;
import com.datastax.oss.driver.api.core.cql.Row;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.DriverConfigLoaderBuilderCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.cql.BeanPropertyRowMapper;
import org.springframework.data.cassandra.core.cql.CqlOperations;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.core.cql.keyspace.CqlStringUtils;

import java.util.List;

@SpringBootApplication
public class ApimCassandraApplication {
    private static final Logger logger = LoggerFactory.getLogger(APIManager.class);


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ApimCassandraApplication.class, args);
//        APIManager apiManager = applicationContext.getBean(APIManager.class);
    //        apiManager.listProxiesAndAccessByAPIName("ptr-partner-vendor-westpac-pymntinvstgtn-v1");
    }


}
