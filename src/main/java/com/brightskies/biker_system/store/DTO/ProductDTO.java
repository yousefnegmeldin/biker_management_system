package com.brightskies.biker_system.store.DTO;

public record ProductDTO
        (
                Long id,
                String name,
                String description,
                String barcode,
                String category,
                double price
        )
{

}
