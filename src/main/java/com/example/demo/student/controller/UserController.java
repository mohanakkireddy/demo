package com.example.demo.student.controller;

import com.example.demo.student.dto.user.ResponseDto;
import com.example.demo.student.dto.user.SigninDto;
import com.example.demo.student.dto.user.SigninResponseDto;
import com.example.demo.student.dto.user.SignupDto;
import com.example.demo.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signUp(signupDto);
    }
    @PostMapping("/signin")
    public SigninResponseDto signIn(@RequestBody SigninDto signinDto){
        return userService.signIn(signinDto);
    }
}
