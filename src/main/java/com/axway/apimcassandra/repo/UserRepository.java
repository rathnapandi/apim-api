package com.axway.apimcassandra.repo;

import com.axway.apimcassandra.model.UserEntity;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface UserRepository extends CassandraRepository<UserEntity, String> {

    UserEntity findByLoginName(String loginName);
}
