package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.helper;

import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllerconstants.OperationConstants;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.request.CustomerRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.response.CustomerResponse;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerCreationHelper {
    private Customer customer;
    private StateMessage stateMessage;
    private CustomerResponse customerResponse;


    public Customer convertRequestToCustomer(CustomerRequest request) {
        // validar si el requeste es nulo - pendiente
        customer = Customer.builder()
                .tipoIdentificacion(request.getTipoIdentificacion())
                .numeroIdentificacion(request.getNumeroIdentificacion())
                .primerNombre(request.getPrimerNombre())
                .segundoNombre(request.getSegundoNombre())
                .primerApellido(request.getPrimerApellido())
                .segundoApellido(request.getSegundoApellido())
                .correoElectronico(request.getCorreoElectronico())
                .fechaNacimiento(request.getFechaNacimiento())
                .fechaModificacion(request.getFechaModificacion())
                .build();
        customer.validCustomer();
        return customer;
    }


    public StateMessage getStateMessages(BussinessException exc) {
        return StateMessage.builder()
                .status(exc.getStatus())
                .detail(exc.getDetail())
                .statusCode(Integer.parseInt(exc.getCode()))
                .build();
    }


    //crear el custumerResponse de exito que va a devolver en el try
    public CustomerResponse createResponse(Customer customer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.DETAIL_OK)
                .build();
        customerResponse.setClient(customer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createResponse(List<Customer> listCustomer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.DETAIL_OK)
                .build();
        customerResponse.setClient(listCustomer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createDeletedUserResponse() {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.STATUS_DETAIL_DELETE)
                .build();
        customerResponse.setClient("{}");
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }

    public CustomerResponse createDeletedUserResponse(Customer customer) {
        customerResponse = new CustomerResponse<>();
        stateMessage = StateMessage.builder()
                .statusCode(200)
                .status(OperationConstants.STATUS_OK)
                .title(OperationConstants.TITLE_OK)
                .detail(OperationConstants.STATUS_DETAIL_DELETE)
                .build();
        customerResponse.setClient(customer);
        customerResponse.setStateMessage(stateMessage);
        return customerResponse;
    }
}
