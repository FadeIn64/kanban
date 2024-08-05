package ru.fedin.treloclient.validation;



import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DataIntervalValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataIntervalCheck {

    String message() default "Data incorrect";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String startDate();

    String endDate();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        DataIntervalCheck[] value();
    }
}
