package com.tqi.avaliacao.custom.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;
import java.util.Date;

public class PrimeiraParcelaValidator implements ConstraintValidator<PrimeiraParcela, Date> {
    @Override
    public void initialize(PrimeiraParcela constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {

        constraintValidatorContext.disableDefaultConstraintViolation();

        if(date == null){
            constraintValidatorContext.buildConstraintViolationWithTemplate("não deve ser nulo").addConstraintViolation();
            return false;
        }

        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 3);
        Date date3MonthsFromNow = calendar.getTime();
        if(date.after(currentDate) && date.before(date3MonthsFromNow)){
            return true;
        }else{
            constraintValidatorContext.buildConstraintViolationWithTemplate("Primeira parcela deve ser a partir da data atual até 3 meses.").addConstraintViolation();
            return false;
        }

    }
}
