package com.example.projectqlch.dto;

import lombok.Data;

@Data
public class CartDTO {
  private double price;
  private String nameProduct;
  private int quantityProduct;
  private String descriptionProduct;
}
