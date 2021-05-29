package com.hackerrank.stocktrades.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ShareValueValidator implements ConstraintValidator<ShareValue, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value<=100 && value>=1;
    }
}
