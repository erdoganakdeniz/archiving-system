package com.archivingsystem.controller;

import com.archivingsystem.entity.Customer;
import com.archivingsystem.http.header.HeaderGenerator;
import com.archivingsystem.service.CustomerService;
import com.archivingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customers=customerService.getAllCustomers();
        if (!customers.isEmpty()) {
            return new ResponseEntity<List<Customer>>(
                    customers,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<List<Customer>>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );

    }
    @GetMapping(value = "/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Long id){
        Customer customer=customerService.getCustomerById(id);
        if (customer!=null) {
            return new ResponseEntity<Customer>(
                    customer,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<Customer>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );
    }
    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer, HttpServletRequest request){
        if(customer != null)
            try {
                customerService.saveCustomer(customer);
                return new ResponseEntity<Customer>(
                        customer,
                        headerGenerator.getHeadersForSuccessPostMethod(request,customer.getId()),
                        HttpStatus.CREATED);
            }catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        return new ResponseEntity<Customer>(HttpStatus.BAD_REQUEST);

    }
    @PutMapping(value = "/{customerId}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable("customerId") Long id,@RequestBody Customer customer){
        Customer newCustomer=customerService.updateCustomerById(id,customer);
        if (newCustomer!=null) {
            return new ResponseEntity<Customer>(
                    newCustomer,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<Customer>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND
        );
    }
    @DeleteMapping(value = "/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") Long id){
        customerService.deleteCustomerById(id);
    }




}
