package com.hellokoding.auth.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hellokoding.auth.model.CreditCard;
@Component
public class CreditCardValidator implements Validator {
	
	String regex = "^(?:(?<visa>4[0-9]{12}(?:[0-9]{3})?)|" +
	        "(?<mastercard>5[1-5][0-9]{14})|" +
	        "(?<discover>6(?:011|5[0-9]{2})[0-9]{12})|" +
	        "(?<amex>3[47][0-9]{13})|" +
	        "(?<diners>3(?:0[0-5]|[68][0-9])?[0-9]{11})|" +
	        "(?<jcb>(?:2131|1800|35[0-9]{3})[0-9]{11}))$";
	 

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditCard.class.equals(aClass);
    }

	@Override
	public void validate(Object o, Errors errors) {
		final CreditCard creditCard = (CreditCard)o;
		final Pattern pattern = Pattern.compile(regex);
	    final Matcher matcher = pattern.matcher(creditCard.getNumber());
		
		if (!matcher.matches())
			errors.rejectValue("number", "CreditCard.number.invalid");
	}

}
