package com.brightskies.biker_system.store.DTO;

import com.brightskies.biker_system.store.model.StockId;
import jakarta.validation.constraints.NotNull;

public record StockDTO
        (
                 @NotNull StockId id,
                 @NotNull Long storeId,
                 @NotNull Long productId,
                 @NotNull int quantity
        )
{
}
