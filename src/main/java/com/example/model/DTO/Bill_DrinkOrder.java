package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bill_DrinkOrder {
    private Long id;
    private String drinkname;
    private String image;
    private double price;
    private int quantity;
    private double total;
}
