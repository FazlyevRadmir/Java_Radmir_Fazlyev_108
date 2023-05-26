//HomeWork 5

package com.fazlyev;

import com.fazlyev.controller.RegistrationController;
import com.fazlyev.model.Customer;
import com.fazlyev.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerUnitTest {
    @Mock
    private CustomerService customerService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRegisterUser() throws Exception {
        RegistrationController registrationController = new RegistrationController(customerService, passwordEncoder);
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();

        Customer customer = new Customer("username", "email@example.com");
        customer.setUsername("testUser");
        customer.setPassword("password");

        when(passwordEncoder.encode(customer.getPassword())).thenReturn("encodedPassword");

        mockMvc.perform(post("/registration")
                        .flashAttr("user", customer))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/authentication"));

        verify(passwordEncoder).encode("password");
        verify(customerService).registerCustomer(any(Customer.class));
    }
}
