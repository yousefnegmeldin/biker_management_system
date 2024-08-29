package com.brightskies.biker_system.biker.mapper;

import com.brightskies.biker_system.biker.dto.BikerDto;
import com.brightskies.biker_system.biker.model.Biker;
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
