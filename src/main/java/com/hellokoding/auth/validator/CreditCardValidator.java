package com.hellokoding.auth.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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

	String regexExipireDate = "^((0[1-9])|(1[0-2]))\\/((2019)|(20[1-2][0-9]))$";

    @Override
    public boolean supports(Class<?> aClass) {
        return CreditCard.class.equals(aClass);
    }

	@Override
	public void validate(Object o, Errors errors) {
		final CreditCard creditCard = (CreditCard)o;
		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(creditCard.getNumber());
		if (!matcher.matches())
			errors.rejectValue("number", "Invalid.ccForm.number");
		pattern = Pattern.compile(regexExipireDate);
	    matcher = pattern.matcher(creditCard.getExpireDateStr());
		if (!matcher.matches())
			errors.rejectValue("expireDateStr", "Invalid.ccForm.expireDateStr"); 
		else {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
			try {
				Date expireDate = dateFormat.parse(creditCard.getExpireDateStr());
				if (expireDate.before(new Date()))
					errors.rejectValue("expireDateStr", "Past.ccForm.expireDateStr"); 
				else
				    creditCard.setExpireDate(dateFormat.parse(creditCard.getExpireDateStr()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
        if (creditCard.getName().length() < 6 || creditCard.getName().length() > 32) {
            errors.rejectValue("name", "Size.ccForm.name");
        }		
		
	}

}
