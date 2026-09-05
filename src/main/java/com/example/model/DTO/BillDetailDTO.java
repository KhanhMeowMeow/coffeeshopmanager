package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BillDetailDTO {
    private Long billId; 
    private Long drinkId; 
    private double unitPrice; 
    private double discount; 
    private int quantity; 
    private boolean disable;
}
