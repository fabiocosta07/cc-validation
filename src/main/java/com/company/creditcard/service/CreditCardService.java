package com.company.creditcard.service;

import java.util.List;

import com.company.creditcard.model.CreditCard;

public interface CreditCardService {
    List<CreditCard> findByNumberContaining(String number);
    List<CreditCard> findByUserName(String userName);
    List<CreditCard> findAll();
    void saveOrUpdate(CreditCard creditCard);
    void deleteById(Long id);
}
