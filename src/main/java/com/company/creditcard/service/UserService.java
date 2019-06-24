package com.company.creditcard.service;

import com.company.creditcard.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
