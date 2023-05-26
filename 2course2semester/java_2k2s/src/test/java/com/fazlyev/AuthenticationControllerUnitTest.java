//HomeWork 5

package com.fazlyev;

import com.fazlyev.controller.AuthenticationController;
import com.fazlyev.model.Customer;
import com.fazlyev.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthenticationControllerUnitTest {
    private AuthenticationController authenticationController;

    @Mock
    private CustomerService customerService;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationController = new AuthenticationController(customerService);
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController)
                .setViewResolvers(new InternalResourceViewResolver())
                .build();
    }

    @Test
    void showAuthenticationPageTest() {
        String page = authenticationController.showAuthenticationPage();
        assertEquals("authenticationPage", page);
    }

    @Test
    public void testAuthenticateUser_SuccessfulAuthentication_RedirectToProfile() {
        AuthenticationController controller = new AuthenticationController(customerService);
        String username = "testUser";
        String password = "testPassword";
        Customer customer = new Customer("username", "email@example.com");
        customer.setUsername(username);
        customer.setPassword(password);

        when(customerService.authenticateCustomer(customer)).thenReturn(true);

        String result = controller.authenticateUser(username, password, model);

        assertEquals("redirect:/profile", result);
        verify(customerService, times(1)).authenticateCustomer(customer);
        verifyNoMoreInteractions(customerService);
        verifyNoInteractions(model);
    }

    @Test
    public void testAuthenticateUser_FailedAuthentication_ReturnAuthenticationPage() {

        AuthenticationController controller = new AuthenticationController(customerService);
        String username = "testUser";
        String password = "testPassword";
        Customer customer = new Customer("username", "email@example.com");
        customer.setUsername(username);
        customer.setPassword(password);

        when(customerService.authenticateCustomer(customer)).thenReturn(false);

        String result = controller.authenticateUser(username, password, model);

        assertEquals("authenticationPage", result);
        verify(customerService, times(1)).authenticateCustomer(customer);
        verifyNoMoreInteractions(customerService);
        verifyNoInteractions(model);
    }
}
