package com.quiz.fullstakequiz.service;

import com.quiz.fullstakequiz.model.User;

import java.util.Collection;

public interface UserService {

    User create(User user);

    Collection<User> list();

    User get(Long id);

    User update(User user);

    Boolean delete(Long id);
}
