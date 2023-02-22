package com.example.projectqlch.Service;

import com.example.projectqlch.Entity.Product;
import com.example.projectqlch.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductDTO createProduct(String name, String description, double price,
                             int quantity, MultipartFile image) throws IOException;

    public byte[] downloadProductImage(String name,Long id) throws IOException;
    ProductDTO searchProduct(ProductDTO product);
    ProductDTO updateProduct(Long id,String name, String description, double price,
                             int quantity, MultipartFile image);

    String deleteProduct(Long productId);
    List<ProductDTO> getAllProducts();

    List<ProductDTO> searchProductByName(String name);
}
