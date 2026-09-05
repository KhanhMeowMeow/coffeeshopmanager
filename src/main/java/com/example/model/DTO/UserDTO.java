package com.example.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class UserDTO {
    private String password;
    private boolean enabled;
    private String fullname;
    private String photo;
    private boolean manager;
    private boolean disable;
}
