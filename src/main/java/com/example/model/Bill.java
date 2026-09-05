package com.example.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Bill { 
    private Long id; 
    private String username; 
    private Long cardId; 
    @Builder.Default 
    private Date checkin = new Date(); 
    private Date checkout; 
    private int status; 
    private boolean disable;
}
