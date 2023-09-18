package com.quiz.fullstakequiz.service.implementation;

import com.quiz.fullstakequiz.dto.AdminDto;
import com.quiz.fullstakequiz.dto.CredentialsDto;
import com.quiz.fullstakequiz.dto.SignUpDto;
import com.quiz.fullstakequiz.exceptions.AppException;
import com.quiz.fullstakequiz.mappers.AdminMapper;
import com.quiz.fullstakequiz.model.Admin;
import com.quiz.fullstakequiz.repo.AdminRepo;
import com.quiz.fullstakequiz.service.AdminService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
@Slf4j // ci permette di vedere su CONSOLE cosa sta succedendo in uno specifico metodo (log)
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepo  adminRepo;
    private final PasswordEncoder passwordEncoder;
    private final AdminMapper adminMapper;

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

    @Override
    public AdminDto login(CredentialsDto credentials) {
        Admin admin = adminRepo.findByAdminName(credentials.adminName())
                .orElseThrow(()-> new AppException("Admin sconosciuto", HttpStatus.NOT_FOUND));

        if(passwordEncoder.matches(CharBuffer.wrap(credentials.password()), admin.getPassword())) {
            return adminMapper.toAdminDto(admin);
        }
        throw new AppException("Password non valida", HttpStatus.BAD_REQUEST);
    }

    @Override
    public AdminDto register(SignUpDto signUp) {
        Optional<Admin> oAdmin = adminRepo.findByAdminName(signUp.adminName());

        if(oAdmin.isPresent()) {
            throw new AppException("Admin già esistente", HttpStatus.BAD_REQUEST);
        }
        Admin admin = adminMapper.signUpToAdmin(signUp);
        admin.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUp.password())));
        Admin savedAdmin = adminRepo.save(admin);
        return adminMapper.toAdminDto(savedAdmin);
    }

    public AdminDto findByAdminName(String adminName) {
        Admin admin = adminRepo.findByAdminName(adminName)
                .orElseThrow(() -> new AppException("Admin Sconosciuto", HttpStatus.NOT_FOUND));
        return adminMapper.toAdminDto(admin);
    }
}
