package com.pawan.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name="user_db")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int  Id;

    @Column()
    private String firstname;

    @Column()
    private String lastname;

    @Column()
    private String email;

    @Column()
    private String password;

    @Column
    private boolean isenabled;



}
