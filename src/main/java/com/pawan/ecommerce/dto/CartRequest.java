package com.pawan.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {

    private float total;


    private String user;


    private int quantity;


    private String product;

}
