package com.archivingsystem.service;

import com.archivingsystem.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomerById(Long id,Customer newCustomer);
    void deleteCustomerById(Long id);
}
