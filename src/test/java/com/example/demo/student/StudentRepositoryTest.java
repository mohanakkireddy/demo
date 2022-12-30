package com.example.demo.student;

import com.example.demo.student.model.Student;
import com.example.demo.student.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static java.time.LocalDate.now;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @Test
    void findStudentByEmail() {
        //given
        String email= "mohan@jmail.com";
        Student student = new Student(
                "Mohan", email , now()
        );
        underTest.save(student);
        //when
        boolean exists = underTest.selectExistsEmail(email);
        //then
        assertThat(exists).isTrue();
    }
}