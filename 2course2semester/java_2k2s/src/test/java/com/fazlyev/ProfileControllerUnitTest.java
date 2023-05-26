//HomeWork 5

package com.fazlyev;

import com.fazlyev.controller.ProfileController;
import com.fazlyev.model.Customer;
import com.fazlyev.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProfileControllerUnitTest {
    private ProfileController profileController;
    @Mock
    private CustomerService customerService;
    @Mock
    private Model model;
    private Customer customer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        profileController = new ProfileController(customerService);
        customer = Customer.builder()
                .username("username")
                .email("email@example.com")
                .build();
    }

    @Test
    void testGetProfilePage() {
        when(customerService.getCurrentUser()).thenReturn(customer);

        String viewName = profileController.getProfilePage(model);

        assertEquals("profilePage", viewName);
        verify(model).addAttribute("username", "username");
        verify(model).addAttribute("email", "email@example.com");
        verify(customerService).getCurrentUser();
    }
}
