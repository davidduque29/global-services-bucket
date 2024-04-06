package com.financesbucket.financialservicemanage.application.customer.usecasecustomer;

import com.financesbucket.financialservicemanage.application.customer.customerservice.CustomerService;
import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.customer.customerport.CustomerGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class UseCaseCustomer implements CustomerService {
    private final CustomerGateway customerGateway;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerGateway.createCustomer(customer);
    }

    @Override
    public Customer updateCustomerId(Long usuarioId, Customer customerInput) {
        return customerGateway.updateCustomerId(usuarioId, customerInput);
    }

    @Override
    public List<Customer> findAllcustomers() {
        return customerGateway.findAllcustomers();
    }

    @Override
    public Customer findcustomerById(Long id) {
        return customerGateway.findcustomerById(id);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerGateway.deleteCustomer(id);
    }

    @Override
    public Customer deleteCustomer(Long id) {
        return customerGateway.deleteCustomer(id);
    }


}
