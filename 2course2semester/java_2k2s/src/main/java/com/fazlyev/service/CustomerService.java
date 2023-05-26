//HomeWork 3

package com.fazlyev.service;

import com.fazlyev.model.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface CustomerService extends UserDetailsService {

    void registerCustomer(Customer customer);

    boolean authenticateCustomer(Customer customer);

    Customer getCurrentUser();

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(Long customerId);

    Customer saveCustomer(Customer customer);
}
