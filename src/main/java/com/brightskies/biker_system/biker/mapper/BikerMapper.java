package com.brightskies.biker_system.biker.mapper;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.model.Biker;
import com.brightskies.biker_system.biker.repository.BikerRepository;
import com.brightskies.biker_system.store.DTO.StockDTO;
import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.Stock;
import com.brightskies.biker_system.store.model.Store;
import com.brightskies.biker_system.store.repository.ProductRepository;
import com.brightskies.biker_system.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BikerMapper {

    public static BikerDto toDTO(Biker biker) {
        return new BikerDto(biker.getId(), biker.getName(), biker.getEmail(), biker.getPhone());
    }


//    public BikerDto toEntity(BikerDto dto){
//        Biker biker = new Biker();
//        biker.setName(dto.getName());
//        biker.setEmail(dto.getEmail());
//        biker.setPhone(dto.getPhone());
//        return biker;
//    }
}
