package com.threeball.threeballcopy.service;

import com.threeball.threeballcopy.entities.User;
import com.threeball.threeballcopy.model.requestDTO.UserRequestDto;

public interface UserService {
    void verifyUser(String email, String Code);
    void create(UserRequestDto userRequestDTO);
    User getUserByEmail(String email);
    User getUserById(int id);
    Boolean  authenticate(String email, String password);
}
