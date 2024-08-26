package com.brightskies.biker_system.store.DTO;

import com.brightskies.biker_system.store.model.StockId;

public record StockDTO
        (
                 StockId id,
                 Long storeId,
                 Long productId,
                 int quantity
        )
{
}
