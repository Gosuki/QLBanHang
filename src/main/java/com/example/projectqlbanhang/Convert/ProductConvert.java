package com.example.projectqlbanhang.Convert;

import com.example.projectqlbanhang.Entity.Product;
import com.example.projectqlbanhang.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
    public ProductDTO toProductDTO(Product product) {
        ProductDTO result = new ProductDTO();
        if(product.getId() != null){
            result.setId(product.getId());
        }
        result.setName(product.getName());
        result.setPrice(product.getPrice());
        result.setQuantity(product.getQuantity());
        result.setImage(product.getProductImage());
        result.setDescription(product.getDescription());
        return result;
    }
    public Product toProduct(ProductDTO productDTO) {
        Product result= new Product();
        result.setId(productDTO.getId());
        result.setName(productDTO.getName());
        result.setPrice(productDTO.getPrice());
        result.setQuantity(productDTO.getQuantity());
        result.setDescription(productDTO.getDescription());
        return result;
    }
}
