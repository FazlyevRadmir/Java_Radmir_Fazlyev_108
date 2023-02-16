package com.fazlyev.controller;

import com.fazlyev.model.User;
import com.fazlyev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HelloController {

    private final UserRepository userRepository;

    @Autowired
    public HelloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = {"/users/{id}", "users"})
    public Iterable<User> user(@PathVariable(required = false) Optional<Integer> id) {
        if (id.isPresent()) {
            return userRepository.findAllById(List.of(id.get()));
        } else {
            return userRepository.findAll();
        }
    }
    //localhost:8080/users
    //localhost:8080/users/1

    @GetMapping("/createUser")
    public String createUser(@RequestParam String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return "User  successfully saved";
    }
    //localhost:8080/createUser?name=John&email=john@example.com

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Optional<Integer> id, @RequestParam Optional<String> name, Optional<String> email) {
        if (id.isPresent()) {
            User updatedUser = new User();
            updatedUser.setId(id.get());
            if (name.isPresent()) {
                updatedUser.setName(name.get());
            }
            if (email.isPresent()) {
                updatedUser.setEmail(email.get());
            }
            userRepository.save(updatedUser);
        }
        return "User successfully update";
    }
    //localhost:8080/updateUser/1?name=John&email=john@example.com

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Optional<Integer> id) {
        if (id.isPresent()) {
            userRepository.deleteById(id.get());
        } else {
            return "User not found";
        }
        return "User deleted";
    }
    //localhost:8080/deleteUser/1

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }
}
