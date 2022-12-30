package com.example.demo.student.controller;

import com.example.demo.student.service.ProgrammaticallyValidatingService;
import com.example.demo.student.service.StudentService;
import com.example.demo.student.dto.Customer;
import com.example.demo.student.dto.Violation;
import com.example.demo.student.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    private final ProgrammaticallyValidatingService programmaticallyValidatingService;

    public StudentController(StudentService studentService,ProgrammaticallyValidatingService programmaticallyValidatingService) {
        this.studentService = studentService;
        this.programmaticallyValidatingService = programmaticallyValidatingService;
    }


    @GetMapping("/get")
    public List <Student> getStudents(){
        return studentService.getStudents();
    }



    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void  updateStudent(@PathVariable("studentId") Long studentId,
                               @RequestParam(required = false) String name,
                               @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
    @PostMapping("/post")
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }
    @PostMapping("/register")
    public List<Violation> submitForm(@RequestBody Customer customer) {
        return programmaticallyValidatingService.validateInputWithInjectedValidator(customer);

    }
    @PutMapping("/update/{studentId}")
    public void editStudent(@PathVariable("studentId") Long studentId, @RequestBody Student student){
        studentService.editStudent(student, studentId);

    }



}




