package com.brightskies.biker_system.store.DTO;

import jakarta.validation.constraints.NotNull;

public record StoreDTO
        (
                @NotNull Long id,
                @NotNull String name,
                @NotNull String area
        )
{
}
