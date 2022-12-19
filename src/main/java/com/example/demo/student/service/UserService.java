package com.example.demo.student.service;

import com.example.demo.student.dto.ResponseDto;
import com.example.demo.student.dto.user.SignupDto;
import com.example.demo.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ResponseDto signUp(SignupDto signupDto) {
        ResponseDto responseDto = new ResponseDto("success", "dummy repo");
        return responseDto;
    }
}
