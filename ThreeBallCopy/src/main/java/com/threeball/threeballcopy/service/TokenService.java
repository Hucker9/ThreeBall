package com.threeball.threeballcopy.service;

import com.threeball.threeballcopy.model.requestDTO.LoginRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String getToken(LoginRequestDto request);
}

