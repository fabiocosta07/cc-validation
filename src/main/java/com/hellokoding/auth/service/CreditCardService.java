package com.hellokoding.auth.service;

import com.hellokoding.auth.model.CreditCard;

import java.util.List;

public interface CreditCardService {
    List<CreditCard> findByNumber(String number);

}
