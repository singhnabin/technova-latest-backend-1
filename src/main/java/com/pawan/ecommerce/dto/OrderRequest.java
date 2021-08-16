package com.pawan.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {


    private String name;

    private String created_by;

    private String Category;

    private float price;

    private String product;

}
