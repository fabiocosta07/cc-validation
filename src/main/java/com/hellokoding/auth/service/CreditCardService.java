package com.hellokoding.auth.service;

import java.util.List;

import com.hellokoding.auth.model.CreditCard;

public interface CreditCardService {
    List<CreditCard> findByNumber(String number);
    List<CreditCard> findByUserName(String userName);
    List<CreditCard> findAll();
    void save(CreditCard creditCard);
    void deleteById(Long id);
}
