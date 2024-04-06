package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer;


import com.financesbucket.financialservicemanage.application.customer.customerservice.CustomerService;
import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.repository.ListTransactionRepository;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.headers.CustomerApiHeadersBuilder;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.helper.CustomerCreationHelper;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.request.CustomerRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.response.CustomerResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Tag(name = "Customer API", description = "Endpoints para gestionar clientes")
@CrossOrigin
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerService customerService;
    private final CustomerCreationHelper creationHelper;
    private CustomerResponse<Customer> customerResponse;

    @Autowired
    ListTransactionRepository listTransactionRepository;
    //http://localhost:8080/financesbucket/swagger-ui.html
    @PostMapping("/createcustomer")
    public ResponseEntity<CustomerResponse<Customer>> addCustomerInformation(
            @RequestBody CustomerRequest customerRequest) {
        customerResponse = new CustomerResponse<>();
        try {
            var customer = customerService.createCustomer(creationHelper
                    .convertRequestToCustomer(customerRequest));
            customerResponse = creationHelper.createResponse(customer);
            return new ResponseEntity<>(customerResponse, CustomerApiHeadersBuilder.buildHeaders(), HttpStatus.OK); // Añadir headers a la respuesta
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, CustomerApiHeadersBuilder.buildHeaders(), HttpStatus.BAD_REQUEST); // Añadir headers a la respuesta
        }
    }


    @PutMapping("/updatecostumer/{id}")
    public ResponseEntity<CustomerResponse<Customer>> updateCustomerRegisterId(@PathVariable("id") Long usuarioId,
                                                                               @RequestBody CustomerRequest customerInput) {
        try {
            var customer = customerService.updateCustomerId(usuarioId, creationHelper
                    .convertRequestToCustomer(customerInput));
            customerResponse = creationHelper.createResponse(customer);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<CustomerResponse<Customer>> deleteCustomer(@PathVariable("id") Long id) {
        try {
            customerResponse = creationHelper.createDeletedUserResponse(customerService.deleteCustomer(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/listcustomers")
    public ResponseEntity<CustomerResponse<Customer>> listCustomers() {
        try {
            List<Customer> customersList = customerService.findAllcustomers();
            customerResponse = creationHelper.createResponse(customersList);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        } catch (BussinessException exc) {
            customerResponse = new CustomerResponse<>();
            customerResponse.setStateMessage(creationHelper.getStateMessages(exc));
            return new ResponseEntity<>(customerResponse, HttpStatus.BAD_REQUEST);
        }
    }
}