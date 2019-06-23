package com.hellokoding.auth.service;

import java.util.List;

import com.hellokoding.auth.model.CreditCard;

public interface CreditCardService {
    List<CreditCard> findByNumberContaining(String number);
    List<CreditCard> findByUserName(String userName);
    List<CreditCard> findAll();
    void saveOrUpdate(CreditCard creditCard);
    void deleteById(Long id);
}
