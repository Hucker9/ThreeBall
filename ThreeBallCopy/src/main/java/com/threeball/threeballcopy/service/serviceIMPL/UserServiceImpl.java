package com.threeball.threeballcopy.service.serviceIMPL;

import com.threeball.threeballcopy.entities.User;
import com.threeball.threeballcopy.exceptions.UserAgeException;
import com.threeball.threeballcopy.exceptions.UserApiException;
import com.threeball.threeballcopy.exceptions.UserNotFoundException;
import com.threeball.threeballcopy.exceptions.WrongEmailException;
import com.threeball.threeballcopy.model.enums.Role;
import com.threeball.threeballcopy.model.enums.Status;
import com.threeball.threeballcopy.model.requestDTO.UserRequestDto;
import com.threeball.threeballcopy.repository.UserRepository;
import com.threeball.threeballcopy.service.UserService;
import com.threeball.threeballcopy.util.Encoder;
import com.threeball.threeballcopy.util.GenerateToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired

    private UserRepository userRepository;
    @Autowired
    private Encoder encoder;

    @Override
    public void create(UserRequestDto userRequestDTO) {
        User user = new User();
        user.setIdusers(0);
        if(userRequestDTO.getName() != null){
            user.setName(userRequestDTO.getName());
        }else{
            throw new NullPointerException("Please write a name");
        }
        if(userRequestDTO.getSurname() != null){
            user.setSurname(userRequestDTO.getSurname());
        }else{
            throw new NullPointerException("Please write a surname");
        }
        if (userRequestDTO.getYear() >= 1900 && userRequestDTO.getYear() <= 2024) {
            user.setYear(userRequestDTO.getYear());
        } else {
            throw new UserAgeException("Wrong year of bird please write 1900 - 2024");
        }
        if (userRequestDTO.getEmail() != null) {
            user.setEmail(userRequestDTO.getEmail());
        } else {
            throw new WrongEmailException("This Email is Not Valid");
        }
        if(userRequestDTO.getPassword() != null ){
            user.setPassword(encoder.encode(userRequestDTO.getPassword()));
        }else{
            throw new NullPointerException("Write password");
        }
        String verifyCode = GenerateToken.generateVerifyCode();
        user.setVerify(verifyCode);
        user.setStatus(Status.IN_ACTIVE);
        user.setRole(Role.GUEST);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserApiException("problem during saving user");
        }
    }

    @Override
    public void verifyUser(String email, String Code) {
        User user = userRepository.getByEmail(email);
        if (user.getVerify().equals(Code)) {
            user.setStatus(Status.ACTIVE);
            user.setVerify(null);
            userRepository.save(user);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.getByEmail(email);
        if(user == null){
            throw new UserNotFoundException("User not found by given email");
        }else{
            return user;
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userRepository.getById(id);
        if(user == null){
            throw new UserNotFoundException("User not found by given Id");
        }else{
            return user;
        }
    }
@Override
    public Boolean authenticate(String email, String password) {
        User user = userRepository.getByEmail(email);
        if (user != null && encoder.matches(password, user.getPassword())) {
            return true; // Authentication successful
        }
        return false; // Authentication failed
    }
}



