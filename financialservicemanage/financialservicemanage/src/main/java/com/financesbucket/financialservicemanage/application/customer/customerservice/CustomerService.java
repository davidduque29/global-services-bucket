package com.financesbucket.financialservicemanage.application.customer.customerservice;

import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomerId(Long usuarioId, Customer customerInput);
    List<Customer> findAllcustomers();
    Customer findcustomerById(Long id);
    Customer deleteCustomer(Long id);
    void deleteCustomerById(Long id);
}

