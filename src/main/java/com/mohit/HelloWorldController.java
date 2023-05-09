package com.mohit;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("/")
public class HelloWorldController {

    private List<User> users = new ArrayList<>();

    @RequestMapping
    public String index() {
        return "BAZEL APPLICATION";
    }

    @GetMapping("user")
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        users.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userToUpdate) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                user.setName(userToUpdate.getName());
                user.setEmail(userToUpdate.getEmail());
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.getId().equals(id)) {
                users.remove(i);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}