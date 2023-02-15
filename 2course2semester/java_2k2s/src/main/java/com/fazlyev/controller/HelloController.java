package com.fazlyev.controller;

import com.fazlyev.model.User;
import com.fazlyev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

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

    @GetMapping("/createUser")
    public String createUser(@RequestParam String name, String email) {
        User user = new User(name, email);
        userRepository.save(user);
        return "User  successfully saved";
    }
    //localhost:8080/createUser?name=John&email=john@example.com

//    @GetMapping("/updateUser/{id}")
//    public String updateUser(@PathVariable Optional<Integer> id, @RequestParam Optional<String> name, Optional<String> email) {
//    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Optional<Integer> id) {
        if (id.isPresent()) {
            userRepository.deleteById(id.get());
        } else {
            return "User not found";
        }
        return "User deleted";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam Optional<String> name) {
        return String.format("Hello, %s!", name.orElse("Ivan"));
    }
}
