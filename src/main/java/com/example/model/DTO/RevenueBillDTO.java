package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RevenueBillDTO {
    private String name;
    private double totalPrice;
    private int totalQuantity;
    private double minPrice;
    private double maxPrice;
    private double avgPrice;
}
