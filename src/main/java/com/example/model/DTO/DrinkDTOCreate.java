package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DrinkDTOCreate {
    private String name;
    private double unitPrice;
    private double discount;
    private String image;
    private boolean available;
    private Long categoryId;
    private boolean disable;
}
