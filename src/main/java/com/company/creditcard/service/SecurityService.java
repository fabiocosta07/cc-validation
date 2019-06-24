package com.company.creditcard.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
