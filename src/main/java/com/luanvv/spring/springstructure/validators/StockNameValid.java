package com.luanvv.spring.springstructure.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = StockNameValidConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StockNameValid {
	String message() default "some default message";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
