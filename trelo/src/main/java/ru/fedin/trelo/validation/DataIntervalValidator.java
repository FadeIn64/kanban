package ru.fedin.trelo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDateTime;

@Slf4j
public class DataIntervalValidator implements ConstraintValidator<DataIntervalCheck, Object> {

    private String startDate;
    private String endDate;
    @Override
    public void initialize(DataIntervalCheck constraintAnnotation) {
        startDate = constraintAnnotation.startDate();
        endDate = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {

        LocalDateTime startDateValue, endDateValue;
        try {
            startDateValue = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(startDate);
            endDateValue = (LocalDateTime) new BeanWrapperImpl(value).getPropertyValue(endDate);
        } catch (ClassCastException e){
            throw new IllegalCallerException("Validated Field is not LocalDateTime");
        }

        log.info("Start Date: {}", startDateValue);
        log.info("End Date: {}", endDate);

        if (startDateValue == null || endDateValue == null)
            return true;

        return endDateValue.isAfter(startDateValue);
    }
}
