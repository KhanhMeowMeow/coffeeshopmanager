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

public class User {
    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    @Builder.Default
    private String photo = "photo.png";
    private boolean manager;
    private boolean disable;
}
