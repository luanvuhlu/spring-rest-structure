package com.luanvv.spring.springstructure.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.luanvv.spring.springstructure.entities.Stock;

public class StockValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> arg0) {
		return Stock.class.equals(arg0);
	}

	@Override
	public void validate(Object arg0, Errors arg1) {
		Stock stock = (Stock) arg0;
		arg1.reject("SomeError");
		// some custom logic..
	}

}
