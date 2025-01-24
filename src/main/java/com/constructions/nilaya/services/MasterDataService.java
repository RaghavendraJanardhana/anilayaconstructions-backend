package com.constructions.nilaya.services;

import com.constructions.nilaya.models.MasterData;
import com.constructions.nilaya.repositories.MasterDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MasterDataService {

    private final MasterDataRepository masterDataRepository;

    @Autowired
    public MasterDataService(MasterDataRepository masterDataRepository) {
        this.masterDataRepository = masterDataRepository;
    }

    // Retrieve all MasterData documents
    public List<MasterData> getAllMasterData() {
        return masterDataRepository.findAll();
    }

    // Retrieve a MasterData document by ID
    public Optional<MasterData> getMasterDataById(String id) {
        return masterDataRepository.findById(id);
    }

    // Save or Update MasterData
    public MasterData saveMasterData(MasterData masterData) {
        return masterDataRepository.save(masterData);
    }

    // Delete MasterData by ID
    public void deleteMasterDataById(String id) {
        masterDataRepository.deleteById(id);
    }
}
