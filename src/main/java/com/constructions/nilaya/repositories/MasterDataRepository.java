package com.constructions.nilaya.repositories;

import com.constructions.nilaya.models.MasterData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterDataRepository extends MongoRepository<MasterData, String> {
}
