package com.example.demo;

import com.example.demo.student.dto.Customer;
import com.example.demo.student.dto.Violation;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProgrammaticallyValidatingService {

  private Validator validator;

  ProgrammaticallyValidatingService(Validator validator) {
    this.validator = validator;
  }

  public List<Violation> validateInputWithInjectedValidator(Customer customer) {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Customer>> violations = validator.validate(customer);
    List<Violation> violationList = new ArrayList<>();
    if (!violations.isEmpty()) {
      for (ConstraintViolation violation : violations) {
        violationList.add(new Violation(violation.getPropertyPath().toString(), violation.getMessageTemplate().toString()));
      }

    }
    return violationList;
  }
}