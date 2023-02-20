package com.example.projectqlch.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private String image;
    private double price;
    private int quantity;

}
