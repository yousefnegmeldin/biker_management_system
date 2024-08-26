package com.brightskies.biker_system.biker.service;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BikerService {
    BikerRepository bikerRepository;

    @Autowired
    public BikerService(BikerRepository bikerRepository){
        this.bikerRepository = bikerRepository;
    }

    public void updateBiker(Long id, BikerDto bikerDTO){
        Biker biker = bikerRepository.findById(id).orElseThrow(() -> new RuntimeException("Biker not found"));
        if(bikerDTO.email() != null)
            biker.setEmail(bikerDTO.email());
        if(bikerDTO.name() != null)
            biker.setName(bikerDTO.name());
        if(bikerDTO.phone() !=null)
            biker.setPhone(bikerDTO.phone());
        bikerRepository.save(biker);

    }

    public void deleteBiker(Long id){
        bikerRepository.deleteById(id);
    }


}
