package com.company.creditcard.repository;

import java.util.List;

import com.company.creditcard.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    List<CreditCard> findByNumberContaining(String number);
    @Query("Select c from CreditCard c where c.number like %:number% and c.user.username = :username")
    List<CreditCard> findByNumberContainingAndUser(@Param("number") String number,@Param("username") String username);
    @Query("Select c from CreditCard c where c.number = :number and c.user.username = :username")
    CreditCard findByNumberAndUser(@Param("number") String number,@Param("username") String username);    
    @Query("Select c from CreditCard c where c.user.username = :username")
    List<CreditCard> findByUserName(@Param("username") String userName);
}
