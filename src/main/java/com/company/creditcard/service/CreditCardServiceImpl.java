package com.company.creditcard.service;

import java.util.List;

import com.company.creditcard.model.CreditCard;
import com.company.creditcard.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> findByNumberContaining(String number) {
        return creditCardRepository.findByNumberContaining(number);
    }

	@Override
	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}
	

	@Override
	public void saveOrUpdate(CreditCard creditCard) {
		final CreditCard savedCard = creditCardRepository.findByNumber(creditCard.getNumber());
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
