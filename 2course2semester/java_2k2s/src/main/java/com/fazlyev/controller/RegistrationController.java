//Independent work

package com.fazlyev.controller;

import com.fazlyev.model.Customer;
import com.fazlyev.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String showRegistrationPage() {
        return "registrationPage";
    }

    @Transactional
    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodedPassword);
        customerService.registerCustomer(customer);
        return "redirect:/authentication";
    }
}
