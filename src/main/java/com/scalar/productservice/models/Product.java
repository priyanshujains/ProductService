package com.scalar.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product  extends base{

    private String title;
    private Double price;
    private String description;

    @ManyToOne
    private Category category;
    private String imageUrl;

}
