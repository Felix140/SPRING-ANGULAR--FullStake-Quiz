package com.quiz.fullstakequiz.resource;


import com.quiz.fullstakequiz.configurations.AdminAuthProvider;
import com.quiz.fullstakequiz.dto.CredentialsDto;
import com.quiz.fullstakequiz.dto.AdminDto;
import com.quiz.fullstakequiz.dto.SignUpDto;
import com.quiz.fullstakequiz.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class AuthAdminController {

    private final AdminService adminService;
    private final AdminAuthProvider adminAuthProvider;



    @PostMapping("/login")
    public ResponseEntity<AdminDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        AdminDto adminDto = adminService.login(credentialsDto);
        adminDto.setToken(adminAuthProvider.createToken(adminDto));
        return ResponseEntity.ok(adminDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AdminDto> register(@RequestBody @Valid SignUpDto admin) {
        AdminDto createdAdmin = adminService.register(admin);
        createdAdmin.setToken(adminAuthProvider.createToken(createdAdmin));
        return ResponseEntity.created(URI.create("/admins/" + createdAdmin.getId())).body(createdAdmin);
    }
}
