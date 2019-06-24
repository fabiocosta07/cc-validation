package com.company.creditcard.service;

import java.util.List;

import com.company.creditcard.model.CreditCard;
import com.company.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> findByNumberContaining(String number) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
            return creditCardRepository.findByNumberContaining(number);
        else 
            return creditCardRepository.findByNumberContainingAndUser(number,authentication.getName());
    }

	@Override
	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}
	

	@Override
	public void saveOrUpdate(CreditCard creditCard) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final CreditCard savedCard = creditCardRepository.findByNumberAndUser(creditCard.getNumber(), authentication.getName());
        
		if (savedCard != null) {
			savedCard.setName(creditCard.getName());
			savedCard.setExpireDate(creditCard.getExpireDate());
			creditCardRepository.save(savedCard);
		}
		else 
			creditCardRepository.save(creditCard);

	}

	@Override
	public void deleteById(Long id) {
		creditCardRepository.deleteById(id);
	}

	@Override
	public List<CreditCard> findByUserName(String userName) {
		return creditCardRepository.findByUserName(userName);
	}


}
