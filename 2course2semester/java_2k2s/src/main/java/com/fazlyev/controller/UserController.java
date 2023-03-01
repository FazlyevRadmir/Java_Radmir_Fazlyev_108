package com.fazlyev.controller;

import com.fazlyev.dto.CreateUserRequestDto;
import com.fazlyev.model.User;
import com.fazlyev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
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

    @PostMapping("/createUser")
    public void createUser(@Valid @ModelAttribute("user") CreateUserRequestDto user) {
        userRepository.save(User.builder()
                .name(user.getName().trim())
                .email(user.getEmail().trim())
                .birthday(user.getBirthday())
                .build());
    }
    //localhost:8080/createUser?name=John&email=john@example.com&birthday=01.01.2020

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable Optional<Integer> id, @RequestParam Optional<String> name, Optional<String> email, Optional<Date> birthday) {
        if (id.isPresent()) {
            User updatedUser = User
                    .builder()
                    .name(name.get())
                    .email(email.get())
//                    .birthday(birthday.get())
                    .build();
            updatedUser.setId(id.get());
            if (name.isPresent()) {
                updatedUser.setName(name.get());
            }
            if (email.isPresent()) {
                updatedUser.setEmail(email.get());
            }
//            if (birthday.isPresent()) {
//                updatedUser.setBirthday(birthday.get());
//            }
            userRepository.save(updatedUser);
        }
        return "User successfully update";
    }
    //localhost:8080/updateUser/1?name=John&email=john@example.com&birthday=2020-10-20

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
