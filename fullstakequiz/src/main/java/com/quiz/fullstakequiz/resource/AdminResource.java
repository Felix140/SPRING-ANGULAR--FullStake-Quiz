package com.quiz.fullstakequiz.resource;

import com.quiz.fullstakequiz.model.Admin;
import com.quiz.fullstakequiz.service.implementation.AdminServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminResource {

    private final AdminServiceImpl adminService;

    //* POST
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.create(admin);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    //* GET (list)
    @GetMapping("/all")
    public ResponseEntity<Collection<Admin>> getAllAdmin() {
        Collection<Admin> allAdmin = adminService.list();
        return new ResponseEntity<>(allAdmin, HttpStatus.OK);
    }

    //* GET
    @GetMapping("/find/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable("id") Long id) {
        Admin adminId = adminService.get(id);
        return new ResponseEntity<>(adminId, HttpStatus.OK);
    }

    //* PUT
    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {
        Admin updateAdmin = adminService.update(admin);
        return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
    }

    //* DELETE
    @Transactional
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Admin> deleteAdmin(@PathVariable("id") Long id){
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
