package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Drink {
    private Long id;
    private String name;
    private double unitPrice;
    private double discount;
    @Builder.Default
    private String image = "product.png";
    private boolean available;
    private Long categoryId;
    private boolean disable;
}
