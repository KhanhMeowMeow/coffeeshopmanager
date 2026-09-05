package com.example.model.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class BillDTOCreate {
    private String username; 
    private Long cardId; 
    private Date checkin; 
    private Date checkout; 
    private int status;
    private boolean disable;
}
