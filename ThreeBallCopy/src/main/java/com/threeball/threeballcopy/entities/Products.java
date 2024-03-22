package com.threeball.threeballcopy.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idproducts;
    @Column(name = "name")
    private String title;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
}
