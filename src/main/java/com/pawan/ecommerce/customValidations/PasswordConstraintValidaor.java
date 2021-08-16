package com.pawan.ecommerce.customValidations;

import org.passay.PasswordValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidaor implements ConstraintValidator<ValidPassword,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        PasswordValidator validator= new PasswordValidator();
        
        return false;
    }

    @Override
    public void initialize(ValidPassword constraintAnnotation) {

    }
}
