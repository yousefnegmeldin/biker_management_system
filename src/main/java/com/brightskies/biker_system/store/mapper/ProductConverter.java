package com.brightskies.biker_system.store.mapper;

import com.brightskies.biker_system.store.DTO.ProductDTO;
import com.brightskies.biker_system.store.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter
{
    public ProductDTO toDTO (Product product)
    {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setBarcode(product.getBarcode());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public Product toEntity(ProductDTO dto)
    {
        Product product= new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setBarcode(dto.getBarcode());
        product.setPrice(dto.getPrice());
        return product;
    }
}
