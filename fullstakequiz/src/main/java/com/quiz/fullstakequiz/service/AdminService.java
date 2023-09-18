package com.quiz.fullstakequiz.service;

import com.quiz.fullstakequiz.dto.AdminDto;
import com.quiz.fullstakequiz.dto.CredentialsDto;
import com.quiz.fullstakequiz.dto.SignUpDto;
import com.quiz.fullstakequiz.model.Admin;

import java.util.Collection;

public interface AdminService {
    Admin create(Admin admin);

    Collection<Admin> list();

    Admin get(Long id);

    Admin update(Admin admin);

    Boolean delete(Long id);

    AdminDto login(CredentialsDto credentials);
    AdminDto register(SignUpDto signUp);
}
