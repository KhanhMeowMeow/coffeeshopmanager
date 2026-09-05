package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrinkDTO {
    private Long id;
    private String name;
    private double unitPrice;
    private double discount;
    private String image;
    private boolean available;
    private String categoryName;
    private boolean disable;
}
