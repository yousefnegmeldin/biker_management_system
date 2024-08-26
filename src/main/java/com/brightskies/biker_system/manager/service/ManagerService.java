package com.brightskies.biker_system.manager.service;

import com.brightskies.biker_system.biker.repository.BikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {

    BikerRepository bikerRepository;
    @Autowired
    public ManagerService(BikerRepository bikerRepository){
        this.bikerRepository = bikerRepository;
    }

}
