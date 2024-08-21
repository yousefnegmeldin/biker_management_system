package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.StoreDTO;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter
{
    public StoreDTO toDTO(Store store)
    {
        StoreDTO dto= new StoreDTO();
        dto.setId(store.getId());
        dto.setName(store.getName());
        dto.setArea(store.getArea());
        dto.setStatus(store.isStatus());
        return dto;
    }

    public Store toEntity(StoreDTO dto)
    {
        Store store = new Store();
        store.setId(dto.getId());
        store.setName(dto.getName());
        store.setArea(dto.getArea());
        store.setStatus(dto.isStatus());
        return store;
    }
}
