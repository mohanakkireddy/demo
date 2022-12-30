package com.example.demo.student.repository;

import com.example.demo.student.model.AuthenticationToken;
import com.example.demo.student.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findByUser (User user);
}
