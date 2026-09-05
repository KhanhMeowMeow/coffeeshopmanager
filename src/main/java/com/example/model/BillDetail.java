package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BillDetail { 
    private Long id; 
    private Long billId; 
    private Long drinkId; 
    private double unitPrice; 
    private double discount; 
    private int quantity; 
    private boolean disable;
} 