package com.financesbucket.financialservicemanage.infrastructure.adapters.customer.mapper;

import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerConverter {

    // Convertir Customer a CustomerEntity
    public static CustomerEntity convertCustomerToEntity(Customer customerDto) {
        CustomerEntity entity = new CustomerEntity();
        entity.setTipoIdentificacion(customerDto.getTipoIdentificacion());
        entity.setNumeroIdentificacion(customerDto.getNumeroIdentificacion());
        entity.setPrimerNombre(customerDto.getPrimerNombre());
        entity.setSegundoNombre(customerDto.getSegundoNombre());
        entity.setPrimerApellido(customerDto.getPrimerApellido());
        entity.setSegundoApellido(customerDto.getSegundoApellido());
        entity.setCorreoElectronico(customerDto.getCorreoElectronico());
        entity.setFechaNacimiento(customerDto.getFechaNacimiento());
        entity.setFechaModificacion(customerDto.getFechaModificacion());
        return entity;
    }

    // Convertir Customer a CustomerEntity
    public static CustomerEntity convertCustomerToEntity(Optional<Customer> customerDto) {
        if (customerDto.isEmpty()) {
            throw new BussinessException("NOT_FOUND", "Customer Optional is empty", "404");
        }

        CustomerEntity entity = new CustomerEntity();
        Customer customer = customerDto.get();

        entity.setId(customer.getId());
        entity.setTipoIdentificacion(customer.getTipoIdentificacion());
        entity.setNumeroIdentificacion(customer.getNumeroIdentificacion());
        entity.setPrimerNombre(customer.getPrimerNombre());
        entity.setSegundoNombre(customer.getSegundoNombre());
        entity.setPrimerApellido(customer.getPrimerApellido());
        entity.setSegundoApellido(customer.getSegundoApellido());
        entity.setCorreoElectronico(customer.getCorreoElectronico());
        entity.setFechaNacimiento(customer.getFechaNacimiento());
        entity.setFechaCreacion(customer.getFechaCreacion());
        entity.setFechaModificacion(customer.getFechaModificacion());

        return entity;
    }

    // Convertir CustomerEntity a Customer
    public static Customer convertEntityToCostumer(CustomerEntity entity) {
        return Customer.builder()
                .id(entity.getId())
                .tipoIdentificacion(entity.getTipoIdentificacion())
                .numeroIdentificacion(entity.getNumeroIdentificacion())
                .primerNombre(entity.getPrimerNombre())
                .segundoNombre(entity.getSegundoNombre())
                .primerApellido(entity.getPrimerApellido())
                .segundoApellido(entity.getSegundoApellido())
                .correoElectronico(entity.getCorreoElectronico())
                .fechaNacimiento(entity.getFechaNacimiento())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaModificacion(entity.getFechaModificacion())
                .build();
    }

    // Convertir CustomerEntity a Customer OPTIONAL
    public static Customer convertEntityToCostumer(Optional<CustomerEntity> entity) {
        return Customer.builder()
                .id(entity.get().getId())
                .tipoIdentificacion(entity.get().getTipoIdentificacion())
                .numeroIdentificacion(entity.get().getNumeroIdentificacion())
                .primerNombre(entity.get().getPrimerNombre())
                .segundoNombre(entity.get().getSegundoNombre())
                .primerApellido(entity.get().getPrimerApellido())
                .segundoApellido(entity.get().getSegundoApellido())
                .correoElectronico(entity.get().getCorreoElectronico())
                .fechaNacimiento(entity.get().getFechaNacimiento())
                .fechaCreacion(entity.get().getFechaCreacion())
                .fechaModificacion(entity.get().getFechaModificacion())
                .build();
    }


    // Convertir una lista de Customer a una lista de CustomerEntity
    public static List<CustomerEntity> convertToEntityList(List<Customer> customerDtos) {
        List<CustomerEntity> entities = new ArrayList<>();
        for (Customer customerDto : customerDtos) {
            entities.add(convertCustomerToEntity(customerDto));
        }
        return entities;
    }

    // Convertir una lista de CustomerEntity a una lista de Customer
    public static List<Customer> convertListEntityToCustomerList(List<CustomerEntity> entities) {
        List<Customer> customerDtos = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            customerDtos.add(convertEntityToCostumer(entity));
        }
        return customerDtos;
    }

    // Convertir una lista de Optional<Customer> a una lista de Optional<CustomerEntity>
    public static List<Optional<CustomerEntity>> convertToEntityOptionalList(List<Optional<Customer>> optionalcustomerDtos) {
        List<Optional<CustomerEntity>> optionalEntities = new ArrayList<>();
        for (Optional<Customer> optionalcustomerDto : optionalcustomerDtos) {
            optionalEntities.add(optionalcustomerDto.map(CustomerConverter::convertCustomerToEntity));
        }
        return optionalEntities;
    }

    // Convertir una lista de Optional<CustomerEntity> a una lista de Optional<Customer>
    public static List<Optional<Customer>> convertTocustomerDtoOptionalList(List<Optional<CustomerEntity>> optionalEntities) {
        List<Optional<Customer>> optionalcustomerDtos = new ArrayList<>();
        for (Optional<CustomerEntity> optionalEntity : optionalEntities) {
            optionalcustomerDtos.add(optionalEntity.map(CustomerConverter::convertEntityToCostumer));
        }
        return optionalcustomerDtos;
    }
}
