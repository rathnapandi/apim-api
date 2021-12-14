package com.axway.apimcassandra.repo;

import com.axway.apimcassandra.model.OrganizationEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface OrganizationRepository extends CassandraRepository<OrganizationEntity, String> {
}
