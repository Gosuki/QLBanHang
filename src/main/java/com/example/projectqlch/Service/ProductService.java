package com.example.projectqlch.Service;

import com.example.projectqlch.Entity.Product;
import com.example.projectqlch.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {
    ProductDTO createProduct(String name, String description, double price,
                             int quantity, MultipartFile image) throws IOException;

    public byte[] downloadProductImage(String name,Long id) throws IOException;
    ProductDTO searchProduct(ProductDTO product);
    ProductDTO updateProduct(Long id,String name, String description, double price,
                             int quantity, MultipartFile image);
}
