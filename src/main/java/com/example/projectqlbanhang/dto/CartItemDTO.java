package com.example.projectqlbanhang.dto;

import lombok.Data;

@Data
public class CartItemDTO {
  private double price;
  private String nameProduct;
  private int quantityProduct;
  private String descriptionProduct;
  private Long idProduct;
  private Long CartItemId;
}
