package com.example.projectqlch.Service.Impl;

import com.example.projectqlch.Convert.ProductConvert;
import com.example.projectqlch.Entity.Product;
import com.example.projectqlch.Repository.ProductRepository;
import com.example.projectqlch.Service.ProductService;
import com.example.projectqlch.dto.ProductDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProductImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductConvert productConvert;

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir")+"/src/main/resources");

    @Override
    public ProductDTO createProduct(String name,
                                 String description,
                                 double price,
                                 int quantity,
                                 MultipartFile image) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("product-images");
        Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = image.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setProductImage(uploadPath.resolve(image.getOriginalFilename()).toString());
        product.setQuantity(quantity);
        product.setPrice(price);
        productRepository.save(product);
        return productConvert.toProductDTO(product);

    }

    @Override
    public byte[] downloadProductImage(String name,Long id) throws IOException {
        Product product= productRepository.findProductByProductImageAndId(name,id);
        String filePath = product.getProductImage();
        return Files.readAllBytes(new File(filePath).toPath());
    }

    @Override
    public ProductDTO searchProduct(ProductDTO product) {
        return null;
    }

    @Override
    public ProductDTO updateProduct(Long id,
                                    String name,
                                    String description,
                                    double price,
                                    int quantity,
                                    MultipartFile image) {
        Product product= productRepository.findProductById(id);
        if(product!=null) {
            product.setName(name);
            product.setDescription(description);
            product.setQuantity(quantity);
            product.setPrice(price);
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(image.getOriginalFilename()));
            Path staticPath = Paths.get("static");
            Path imagePath = Paths.get("product-images");
            Path uploadPath = Paths.get(String.valueOf(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath)));
            Path filePath = uploadPath.resolve(fileName);
            try (OutputStream os = Files.newOutputStream(filePath)) {
                os.write(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            product.setProductImage(uploadPath.resolve(image.getOriginalFilename()).toString());
            productRepository.save(product);
            return productConvert.toProductDTO(product);
       }else {
            return null;
        }
    }
}
