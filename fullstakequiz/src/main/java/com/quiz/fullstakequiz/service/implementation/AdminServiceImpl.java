package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.model.Admin;
import com.quiz.fullstakequiz.repo.AdminRepo;
import com.quiz.fullstakequiz.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepo  adminRepo;

    @Override
    public Admin create(Admin admin) {
        log.info("Nuovo USER salvato: {}", admin.getAdminName());
        return adminRepo.save(admin);
    }

    @Override
    public Collection<Admin> list() {
        log.info("Recupero tutti gli ADMIN...");
        return adminRepo.findAll();
    }

    @Override
    public Admin get(Long id) {
        log.info("ADMIN recuperato: {}", id);
        return adminRepo.findById(id).get();
    }

    @Override
    public Admin update(Admin admin) {
        log.info("USER in aggiornamento: {}", admin.getAdminName());
        return adminRepo.save(admin);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("ID ADMIN Eliminato: {}", id);
        adminRepo.deleteById(id);
        return Boolean.TRUE;
    }
}
