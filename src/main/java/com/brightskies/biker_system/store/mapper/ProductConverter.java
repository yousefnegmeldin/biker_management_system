package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter
{

    public ProductDTO toDTO (Product product)
    {
       return new ProductDTO(
               product.getId(),
               product.getName(),
               product.getDescription(),
               product.getBarcode(),
               product.getCategory(),
               product.getPrice()
       );
    }

    public Product toEntity(ProductDTO dto)
    {
        return new Product(
                dto.name(),
                dto.description(),
                dto.barcode(),
                dto.category(),
                dto.price()
        );
    }
}
