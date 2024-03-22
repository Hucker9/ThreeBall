package com.threeball.threeballcopy.controller;
import com.threeball.threeballcopy.model.requestDTO.LoginRequestDto;
import com.threeball.threeballcopy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity<?> getToken(@RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(tokenService.getToken(request));
    }
}
