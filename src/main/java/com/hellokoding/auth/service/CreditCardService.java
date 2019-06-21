package com.hellokoding.auth.service;

import com.hellokoding.auth.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    void save(CreditCard creditCard);
    List<CreditCard> findByNumber(String number);
    List<CreditCard> findAll();
    void deleteById(Long id);
}
