package com.example.demo.student.dto;

import javax.validation.constraints.NotNull;

public class Customer {


       @NotNull(message = "Name field is null")
        private String name;

        @NotNull(message = "Email is null")
        private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
