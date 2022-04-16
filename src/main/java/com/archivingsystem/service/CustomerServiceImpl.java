package com.archivingsystem.service;

import com.archivingsystem.entity.Customer;
import com.archivingsystem.entity.User;
import com.archivingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public Customer updateCustomerById(Long id,Customer newCustomer) {
        Optional<Customer> customer=customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer foundCustomer=customer.get();
            foundCustomer.setFirstName(newCustomer.getFirstName());
            foundCustomer.setLastName(newCustomer.getLastName());
            foundCustomer.setAddress(newCustomer.getAddress());
            foundCustomer.setEmail(newCustomer.getEmail());
            foundCustomer.setIdentificationNumber(newCustomer.getIdentificationNumber());
            foundCustomer.setPhoneNumber(newCustomer.getPhoneNumber());
            foundCustomer.setCompanyName(newCustomer.getCompanyName());
            customerRepository.save(foundCustomer);
            return foundCustomer;
        }else{
            return null;
        }

    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);

    }
}
