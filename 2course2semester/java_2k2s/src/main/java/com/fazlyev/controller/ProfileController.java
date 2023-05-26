//Independent work

package com.fazlyev.controller;

import com.fazlyev.model.Customer;
import com.fazlyev.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final CustomerService customerService;

    public ProfileController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        Customer customer = customerService.getCurrentUser();
        model.addAttribute("username", customer.getUsername());
        model.addAttribute("email", customer.getEmail());
        return "profilePage";
    }
}