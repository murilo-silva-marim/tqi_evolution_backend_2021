package com.tqi.avaliacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Configuration
public class EmailValidator implements ConstraintValidator<EmailUnico, String> {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public void initialize(EmailUnico constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(clientesRepository.existsByEmail(email)){
            return false;
        }else{
            return true;
        }

    }
}
