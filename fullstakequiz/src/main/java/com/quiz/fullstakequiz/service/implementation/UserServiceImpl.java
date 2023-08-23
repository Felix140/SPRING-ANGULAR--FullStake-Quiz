package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.model.User;
import com.quiz.fullstakequiz.repo.UserRepo;
import com.quiz.fullstakequiz.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User create(User user) {
        log.info("Nuovo USER salvato: {}", user.getUserName());
        return userRepo.save(user);
    }

    @Override
    public Collection<User> list() {
        log.info("Recupero tutti gli USER...");
        return userRepo.findAll();
    }

    @Override
    public User get(Long id) {
        log.info("USER recuperato: {}", id);
        return userRepo.findById(id).get();
    }

    @Override
    public User update(User user) {
        log.info("USER in aggiornamento: {}", user.getUserName());
        return userRepo.save(user);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("ID USER Eliminato: {}", id);
        userRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
