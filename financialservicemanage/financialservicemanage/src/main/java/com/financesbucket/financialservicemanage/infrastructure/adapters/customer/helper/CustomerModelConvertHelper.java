package com.financesbucket.financialservicemanage.infrastructure.adapters.customer.helper;

import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerModelConvertHelper {
    public CustomerEntity convertModelToCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .tipoIdentificacion(customer.getTipoIdentificacion())
                .numeroIdentificacion(customer.getNumeroIdentificacion())
                .primerNombre(customer.getPrimerNombre())
                .segundoNombre(customer.getSegundoNombre())
                .primerApellido(customer.getPrimerApellido())
                .segundoApellido(customer.getSegundoApellido())
                .correoElectronico(customer.getCorreoElectronico())
                .fechaNacimiento(customer.getFechaNacimiento())
                .fechaCreacion(customer.getFechaCreacion())
                .fechaModificacion(customer.getFechaModificacion())
                .build();

    }

}
