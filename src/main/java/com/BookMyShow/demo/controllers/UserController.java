package com.BookMyShow.demo.controllers;

import com.BookMyShow.demo.dtos.ResponseStatus;
import com.BookMyShow.demo.dtos.SignUpUserDtoResponse;
import com.BookMyShow.demo.dtos.SignUpUserRequestDto;
import com.BookMyShow.demo.models.User;
import com.BookMyShow.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService ;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService ;
    }

    public SignUpUserDtoResponse signUp(SignUpUserRequestDto request){
        User user = userService.signUp(request) ;
        return new SignUpUserDtoResponse(user.getId(), ResponseStatus.SUCESS);
    }

    public boolean login (String email , String password){
        return userService.login(email,password) ;
    }
}
