package com.hellokoding.auth.service;

import com.hellokoding.auth.model.CreditCard;
import com.hellokoding.auth.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardServiceImpl implements CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public List<CreditCard> findByNumber(String number) {
        return creditCardRepository.findByNumberContaining(number);
    }

	@Override
	public List<CreditCard> findAll() {
		return creditCardRepository.findAll();
	}

	@Override
	public void save(CreditCard creditCard) {
		creditCardRepository.save(creditCard);
	}

	@Override
	public void deleteById(Long id) {
		creditCardRepository.deleteById(id);
	}


}
