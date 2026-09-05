package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DrinkOrderDTO {
    private Long id;
    private String name;
    private double unitPrice;
    private double discount;
    private String image;
    private String categoryname;
    private boolean available;
}
