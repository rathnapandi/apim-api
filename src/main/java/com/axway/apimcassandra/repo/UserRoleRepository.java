package com.axway.apimcassandra.repo;

import com.axway.apimcassandra.model.UserRole;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRoleRepository extends CassandraRepository<UserRole, String> {
    UserRole findByUserDn(String userDn);
}
