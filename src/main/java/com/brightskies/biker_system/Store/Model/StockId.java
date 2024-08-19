package com.brightskies.biker_system.Store.Model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class StockId implements Serializable {

    private long storeId;
    private long productId;

    // Default constructor
    public StockId() {}

    // Constructor
    public StockId(int storeId, int productId) {
        this.storeId = storeId;
        this.productId = productId;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockId stockId = (StockId) o;
        return storeId == stockId.storeId && productId == stockId.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, productId);
    }
}
