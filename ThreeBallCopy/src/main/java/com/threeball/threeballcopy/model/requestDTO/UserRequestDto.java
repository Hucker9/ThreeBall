package com.threeball.threeballcopy.model.requestDTO;

import lombok.Data;

@Data
public class UserRequestDto {
    private String name;
    private String surname;
    private int year;
    private String email;
    private String password;
}
