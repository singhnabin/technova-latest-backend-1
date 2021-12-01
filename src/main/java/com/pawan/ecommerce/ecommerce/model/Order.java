package com.pawan.ecommerce.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="Order_db")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Column
    private String name;
    @Column
    private String created_by;
    @Column
    private String Category;
    @Column
    private float price;
    @Column
    private String product;
    @Column
    private Integer quantity;

    @Column
    private String deleveryStatus;

    @Column
    private int orderProgress;

    @Column
    private String orderReceipt;

    @Column
    private String transactionId;
}
