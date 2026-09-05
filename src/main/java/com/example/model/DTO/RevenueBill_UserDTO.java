package com.example.model.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class RevenueBill_UserDTO {
    private Long id;
    private Long cardId;
    private Date checkin; 
    private Date checkout; 
    private int status;
}
