package com.brightskies.biker_system.store.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class StockId implements Serializable {

    private Long storeId;
    private Long productId;

    // Default constructor
    public StockId() {}

    // Constructor
    public StockId(Long storeId, Long productId) {
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
