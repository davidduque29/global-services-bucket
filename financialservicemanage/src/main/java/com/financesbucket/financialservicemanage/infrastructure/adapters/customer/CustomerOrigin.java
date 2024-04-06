package com.financesbucket.financialservicemanage.infrastructure.adapters.customer;

import com.financesbucket.financialservicemanage.application.customer.customerhelper.UserDataValidation;
import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.customer.customerport.CustomerGateway;

import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.exceptions.ExceptionUtil;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;

import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.helper.CustomerModelConvertHelper;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.mapper.CustomerConverter;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.repository.CustomerCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.repository.UserQueryRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository.ProductQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerOrigin implements CustomerGateway {

    private final CustomerCrudRepository customerCrudRepository;
    private final ProductQueryRepository productQueryRepository;
    private final UserQueryRepository userQueryRepository;
    private CustomerEntity customerEntity;

    @Override
    public Customer createCustomer(Customer customerModel) {
        boolean isUserExistence = validateUserExistence(customerModel.getNumeroIdentificacion());
        customerEntity = convertCustomerModelToEntity(customerModel);// Usada en la siguiente llamada
        return CustomerConverter.convertEntityToCostumer(customerCrudRepository.save(customerEntity));
    }


    public CustomerEntity convertCustomerModelToEntity(Customer customerModel) {
        var customerEntityData = transformModelTOEntity(customerModel);
        return completeCustomerData(customerEntityData);
    }

    public CustomerEntity transformModelTOEntity(Customer customerModel) {
        var customerEntityData = new CustomerEntity();
        CustomerModelConvertHelper customerData = new CustomerModelConvertHelper();
        customerEntityData = customerData.convertModelToCustomerEntity(customerModel);
        return customerEntityData;
    }

    public CustomerEntity completeCustomerData(CustomerEntity customerEntity) {
        Date fechaActual = new Date();
        UserDataValidation formatearFechaNacimiento = new UserDataValidation();
        customerEntity.setFechaNacimiento(formatearFechaNacimiento.formattedDate(customerEntity).getFechaNacimiento());
        customerEntity.setFechaModificacion(fechaActual);
        return customerEntity;
    }

    public boolean validateUserExistence(String numeroIdentificacion) throws BussinessException {
        if (userExists(numeroIdentificacion)) {
            throw new BussinessException(ExceptionUtil.EXISTING_USER_EXCEPTION_STATUS,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_DETAIL,
                    ExceptionUtil.EXISTING_USER_EXCEPTION_CODE);
        }
        return true;
    }

    private boolean userExists(String numeroIdentificacion) {
        return findUserExistence(numeroIdentificacion);
    }

    public boolean findUserExistence(String identification) {
        String existingUser = userQueryRepository.findNumberIdentificationByNumberIdentification(identification);
        return existingUser != null;
    }

    @Override
    public Customer updateCustomerId(Long usuarioId, Customer customerModel) {
        return customerCrudRepository.findById(usuarioId).map(customer -> {
            customer.setTipoIdentificacion(customerModel.getTipoIdentificacion());
            customer.setNumeroIdentificacion(customerModel.getNumeroIdentificacion());
            customer.setPrimerNombre(customerModel.getPrimerNombre());
            customer.setSegundoNombre(customerModel.getSegundoNombre());
            customer.setPrimerApellido(customerModel.getPrimerApellido());
            customer.setSegundoApellido(customerModel.getSegundoApellido());
            customer.setCorreoElectronico(customerModel.getCorreoElectronico());
            customer.setFechaNacimiento(customerModel.getFechaNacimiento());
            customer.setFechaModificacion(new Date());
            return CustomerConverter.convertEntityToCostumer(customerCrudRepository.save(customer));
        }) .orElseThrow(() -> new BussinessException("CLIENTE_NO_EXISTE", "El cliente con ID "
                + usuarioId + " no existe","404" ));

    }

    @Override
    public List<Customer> findAllcustomers() {
        return CustomerConverter.convertListEntityToCustomerList(customerCrudRepository.findAll());
    }

    @Override
    public Customer findcustomerById(Long id) {
        return CustomerConverter.convertEntityToCostumer(customerCrudRepository.findById(id));
    }


    @Override
    public Customer deleteCustomer(Long id) {
        CustomerEntity customerToDelete = customerCrudRepository.findById(id)
                .orElseThrow(() -> new BussinessException("CLIENTE_NO_EXISTE"
                        , "El cliente con ID " + id + " no existe"
                        , "404"));

        int productCount = productQueryRepository.countProductsByClient(customerToDelete.getId());
        if (productCount > 0) {
            throw new BussinessException("PRODUCTOS_ASOCIADOS"
                    , "No se puede eliminar el cliente debido a productos asociados"
                    , "400");
        }

        customerCrudRepository.delete(customerToDelete);
        return CustomerConverter.convertEntityToCostumer(customerToDelete);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerCrudRepository.deleteById(id);
    }
}
