package com.brightskies.biker_system.store.DTO;

import jakarta.validation.constraints.NotNull;

public record ProductDTO
        (
                @NotNull Long id,
                @NotNull String name,
                String description,
                String barcode,
                String category,
                @NotNull double price
        )
{

}
