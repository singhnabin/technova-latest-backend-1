package com.pawan.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {


    private String name;
    private float price;
    private String description;
    private int quantity;
    private String created_by;
    private String category;
    private MultipartFile image;



}
