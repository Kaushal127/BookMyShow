package com.BookMyShow.demo.services;

import com.BookMyShow.demo.dtos.SignUpUserRequestDto;
import com.BookMyShow.demo.models.User;
import com.BookMyShow.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository ;
    public User signUp(SignUpUserRequestDto request) {
        // We want to make sure that this is new user
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        if (user.isPresent()) {
            throw new RuntimeException() ;
        }

        User newUser = new User() ;
        newUser.setName(request.getName());
        newUser.setEmail(request.getEmail());
        String password = request.getPassword() ;
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder() ;

        newUser.setPassword(encoder.encode(password));

        return userRepository.save(newUser) ;

    }

    public boolean login(String email, String password) {

        Optional<User> user = userRepository.findByEmail(email) ;
        if(!user.isPresent()){
            throw new RuntimeException() ;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder() ;

        return encoder.matches(password , user.get().getPassword());
    }
}
