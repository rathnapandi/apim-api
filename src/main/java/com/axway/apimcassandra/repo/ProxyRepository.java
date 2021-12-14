package com.axway.apimcassandra.repo;

import com.axway.apimcassandra.model.ApplicationEntity;
import com.axway.apimcassandra.model.FrontendAPI;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface ProxyRepository extends CassandraRepository<FrontendAPI, String> {
    List<FrontendAPI> findByName(String name);

}
