package com.hellokoding.auth.repository;

import com.hellokoding.auth.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByNumberContaining(String number);
}
