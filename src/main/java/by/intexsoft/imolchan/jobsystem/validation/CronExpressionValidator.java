package by.intexsoft.imolchan.jobsystem.validation;

import org.springframework.scheduling.support.CronExpression;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CronExpressionValidator implements ConstraintValidator<ValidCronExpression, String> {
    @Override
    public void initialize(ValidCronExpression constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return CronExpression.isValidExpression(s);
    }
}
