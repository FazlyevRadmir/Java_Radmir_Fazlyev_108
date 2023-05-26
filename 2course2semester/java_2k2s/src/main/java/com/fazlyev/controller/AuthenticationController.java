//Independent work

package com.fazlyev.controller;

import com.fazlyev.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.fazlyev.service.CustomerService;

@Controller
public class AuthenticationController {

    private final CustomerService customerService;

    @Autowired
    public AuthenticationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/authentication")
    public String showAuthenticationPage() {
        return "authenticationPage";
    }

    @Transactional
    @PostMapping("/authentication")
    public String authenticateUser(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
        Customer customer = new Customer("username", "email@example.com");
        customer.setUsername(username);
        customer.setPassword(password);

        if (customerService.authenticateCustomer(customer)) {
            return "redirect:/profile";
        } else {
            return "authenticationPage";
        }
    }

}

