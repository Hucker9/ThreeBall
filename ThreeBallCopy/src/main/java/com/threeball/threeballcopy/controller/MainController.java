package com.threeball.threeballcopy.controller;
import com.threeball.threeballcopy.config.JwtUtil;
import com.threeball.threeballcopy.entities.User;
import com.threeball.threeballcopy.model.requestDTO.LoginRequestDto;
import com.threeball.threeballcopy.model.requestDTO.UserRequestDto;
import com.threeball.threeballcopy.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/ThreeBall")
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDto userRequestDTO) {
        userService.create(userRequestDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public String login(Model model, LoginRequestDto loginRequestDto) {
        model.addAttribute("user", loginRequestDto);
        return "login";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        try {
            if(userService.authenticate(loginRequestDto.getEmail(),loginRequestDto.getPassword())){

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword()));

                String email = authentication.getName();

                User userByEmail = userService.getUserByEmail(email);

                String token = jwtUtil.createToken(userByEmail);

                response.setHeader("Authorization", "Bearer " + token);

                response.sendRedirect("/ThreeBall/home");

                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while getting token");
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/register")
    public String register(Model model, UserRequestDto userRequestDto) {
        model.addAttribute("user", userRequestDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSave(@ModelAttribute("user") UserRequestDto userRequestDto) {
        userService.create(userRequestDto);
        return "registerSuccess";
    }
}
