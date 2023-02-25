package com.example.projectqlbanhang.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;

}
