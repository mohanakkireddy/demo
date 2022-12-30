package com.example.demo.student.service;

import com.example.demo.student.model.AuthenticationToken;
import com.example.demo.student.model.User;
import com.example.demo.student.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {

        tokenRepository.save(authenticationToken);

    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser (user);
    }
}
