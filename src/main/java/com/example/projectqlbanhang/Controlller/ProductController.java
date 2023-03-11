package com.example.projectqlbanhang.Controlller;

import com.example.projectqlbanhang.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestParam String name,
                                           @RequestParam String description,
                                           @RequestParam double price,
                                           @RequestParam int quantity,
                                           @RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok(productService.createProduct(name, description, price, quantity, image));
    }

    @GetMapping("/{id}/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName,@PathVariable Long id) throws IOException {
        byte[] imageData=productService.downloadProductImage(fileName,id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);

    }
    @PutMapping("/update/{productId}")
    public ResponseEntity<?> updateProduct(@PathVariable Long productId,
                                           @RequestParam String name,
                                           @RequestParam String description,
                                           @RequestParam double price,
                                           @RequestParam int quantity,
                                           @RequestParam MultipartFile image){
        return ResponseEntity.ok(productService.updateProduct(productId,name, description, price, quantity, image));
    }
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
    @GetMapping("/all")
    public ResponseEntity<?> getProduct(){
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchProduct(@RequestParam String name){
        return ResponseEntity.ok(productService.searchProductByName(name));
    }

}
