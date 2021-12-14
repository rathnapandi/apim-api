package com.axway.apimcassandra.repo;

import com.axway.apimcassandra.model.ApplicationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;

public interface ApplicationRepository extends CassandraRepository<ApplicationEntity, String> {
    // CREATE INDEX api_server_portalapplicationstore_name_idx ON westpac.api_server_portalapplicationstore (name);
    List<ApplicationEntity> findByName(String name);
}
