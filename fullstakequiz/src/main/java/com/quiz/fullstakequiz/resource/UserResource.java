package com.quiz.fullstakequiz.resource;

import com.quiz.fullstakequiz.model.User;
import com.quiz.fullstakequiz.service.implementation.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final UserServiceImpl userService;

    //* POST
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.create(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    //* GET (list)
    @GetMapping("/all")
    public ResponseEntity<Collection<User>> getAllUsers() {
        Collection<User> allUsers = userService.list();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    //* GET
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User userId = userService.get(id);
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }

    //* PUT
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updateUser = userService.update(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    //* DELETE
    @Transactional
    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
