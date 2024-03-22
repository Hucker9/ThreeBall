package com.threeball.threeballcopy.service.serviceIMPL;

import com.threeball.threeballcopy.config.JwtUtil;
import com.threeball.threeballcopy.entities.User;
import com.threeball.threeballcopy.model.requestDTO.LoginRequestDto;
import com.threeball.threeballcopy.repository.UserRepository;
import com.threeball.threeballcopy.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository repository;

    @Override
    public String getToken(LoginRequestDto loginRequest) {
        String token = null;
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            String email = authentication.getName();
            User userByEmail = repository.getByEmail(email);
            token = jwtUtil.createToken(userByEmail);
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid username or password");
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while getting token");
        }
        return token;
    }
}
