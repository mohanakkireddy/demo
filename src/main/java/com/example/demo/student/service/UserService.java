package com.example.demo.student.service;

import com.example.demo.student.dto.user.ResponseDto;
import com.example.demo.student.dto.user.SigninDto;
import com.example.demo.student.dto.user.SigninResponseDto;
import com.example.demo.student.dto.user.SignupDto;
import com.example.demo.student.exceptions.AuthenticationFailException;
import com.example.demo.student.exceptions.CustomException;
import com.example.demo.student.model.AuthenticationToken;
import com.example.demo.student.model.User;
import com.example.demo.student.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Transactional
    public ResponseDto signUp(SignupDto signupDto) {

        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))){
            throw new CustomException("user already present");
        }
        //Hashing the password
        String encryptedpassword = signupDto.getPassword();
        try{
            encryptedpassword= hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e){
            e.printStackTrace();

        }

        User user = new User(signupDto.getFirstName(), signupDto.getLastName(), signupDto.getEmail(), encryptedpassword);
        userRepository.save(user);

        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);


        ResponseDto responseDto = new ResponseDto("success", "User Created Successfully");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDto signIn(SigninDto signinDto) {
        User user= userRepository.findByEmail(signinDto.getEmail());
        if(Objects.isNull(user)) {
            throw new AuthenticationFailException("user is not valid");
        }
        try {
            if (!user.getPassword().equals(hashPassword(signinDto.getPassword()))){
                throw new AuthenticationFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken token = authenticationService.getToken(user);
        if(Objects.isNull(token)) {
            throw new CustomException("user is not valid");
        }
        return new SigninResponseDto("success", token.getToken());

    }
}
