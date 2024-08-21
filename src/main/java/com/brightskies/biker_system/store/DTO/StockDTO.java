package com.brightskies.biker_system.store.DTO;

import com.brightskies.biker_system.store.model.Product;
import com.brightskies.biker_system.store.model.StockId;
import com.brightskies.biker_system.store.model.Store;
import lombok.Data;

@Data
public class StockDTO
{
    private StockId id;
    private Long storeId;
    private Long productId;
    private int quantity;

}
