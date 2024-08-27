package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.StoreDTO;
import com.brightskies.biker_system.store.model.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreConverter
{
    public StoreDTO toDTO(Store store)
    {
        return new StoreDTO
                (
                        store.getId(),
                        store.getName(),
                        store.getArea(),
                        store.isStatus()
                );
    }

    public Store toEntity(StoreDTO dto)
    {
        Store store = new Store();
        store.setId(dto.id());
        store.setName(dto.name());
        store.setArea(dto.area());
        store.setStatus(dto.status());
        return store;
    }
}
