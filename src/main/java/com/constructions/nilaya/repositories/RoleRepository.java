package com.constructions.nilaya.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.constructions.nilaya.models.ERole;
import com.constructions.nilaya.models.Role;


public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
