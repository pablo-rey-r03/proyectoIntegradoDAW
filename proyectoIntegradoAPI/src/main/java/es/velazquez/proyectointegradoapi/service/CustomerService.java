package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);
    Customer getCustomerByUserEmail(String userEmail);
    List<Customer> getAllCustomers();
    Customer saveCustomer(Customer customer);
    void deleteCustomer(Long id);
}
