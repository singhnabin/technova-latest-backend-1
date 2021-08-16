package com.pawan.ecommerce.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private String description;

    @Column
    private int quantity;

    @Column
    private String created_by;

    @Column
    private String category;

    @Column
    private String imageURL;





}
