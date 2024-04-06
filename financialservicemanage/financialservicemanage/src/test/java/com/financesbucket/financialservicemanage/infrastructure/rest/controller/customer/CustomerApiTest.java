package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.financesbucket.financialservicemanage.application.customer.customerservice.CustomerService;
import com.financesbucket.financialservicemanage.application.customer.usecasecustomer.UseCaseCustomer;
import com.financesbucket.financialservicemanage.domain.customer.customermodel.Customer;
import com.financesbucket.financialservicemanage.domain.customer.customerport.CustomerGateway;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.CustomerOrigin;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.repository.UserQueryRepository;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.helper.CustomerCreationHelper;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.request.CustomerRequest;
import com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.response.CustomerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CustomerApiTest {

    @InjectMocks
    CustomerApi customerServiceApi;

    @Mock
    private CustomerService customerService;

    @Mock
    private CustomerOrigin customerOrigin;

    @Mock
    UseCaseCustomer useCaseCustomer;

    @Mock
    CustomerGateway customerGateway;

    @Mock
    private UserQueryRepository userRepository;

    private MockMvc mockMvc;

    private CustomerRequest customerRequest;
    private Customer customer;
    private Long usuarioId;
    private Date fecha;
    private CustomerEntity entity;
    private List<CustomerEntity> customerEntitiesList;
    private CustomerCreationHelper creationHelper;

    @BeforeEach
    void setUp() {
        customerRequest = new CustomerRequest();
        customerEntitiesList = new ArrayList<>();
        Long id= 1L;

        usuarioId = 1l;
        fecha = new Date();
        creationHelper = new CustomerCreationHelper();
        customer =  Customer.builder()
                .id(usuarioId)
                .tipoIdentificacion("cedula")
                .numeroIdentificacion("1054549309")
                .primerNombre("Ivan")
                .segundoNombre("DAVID")
                .primerApellido("Duque")
                .segundoApellido("Perdomo")
                .correoElectronico("ivduque@hotmail.com")
                .fechaNacimiento(fecha)
                .fechaCreacion(fecha)
                .fechaModificacion(fecha)
                .build();
        customerRequest.setTipoIdentificacion("cedula");
        customerRequest.setNumeroIdentificacion("1054549309");
        customerRequest.setPrimerNombre("Ivan");
        customerRequest.setSegundoNombre("David");
        customerRequest.setPrimerApellido("Duque");
        customerRequest.setSegundoApellido("Perdomo");
        customerRequest.setCorreoElectronico("ivduque@hotmail.com");
        customerRequest.setFechaNacimiento(fecha);
        customerRequest.setFechaModificacion(fecha);
        entity = new CustomerEntity();
        entity.setId(usuarioId);
        entity.setTipoIdentificacion("cedula");
        entity.setNumeroIdentificacion("1054549309");
        entity.setPrimerNombre("Carlos");
        entity.setSegundoNombre("Andres");
        entity.setPrimerApellido("Duque");
        entity.setSegundoApellido("Perdomo");
        entity.setCorreoElectronico("ivduque@hotmail.com");
        entity.setFechaNacimiento(fecha);
        entity.setFechaCreacion(fecha);
        entity.setFechaModificacion(fecha);
        customer.setId(1L); // ID ficticio
        customer.setTipoIdentificacion("Cedula");
        customer.setNumeroIdentificacion("123456789");
        customer.setPrimerNombre("Juan");
        customer.setSegundoNombre("Pablo");
        customer.setPrimerApellido("Gonzalez");
        customer.setSegundoApellido("Lopez");
        customer.setCorreoElectronico("juan@example.com");
        customer.setFechaNacimiento(new Date()); // Fecha de nacimiento ficticia
        customer.setFechaCreacion(new Date()); // Fecha de creación ficticia
        customer.setFechaModificacion(new Date());
    }

    @Test
    void testAddCustomerInformation_Success() {
        // Configurar objetos mock
        CustomerService customerServiceMock =  Mockito.mock(CustomerService.class);
        CustomerCreationHelper creationHelperMock =  Mockito.mock(CustomerCreationHelper.class);
        HttpHeaders headersMock =  Mockito.mock(HttpHeaders.class);

        // Configurar el resultado de los mocks
        customerRequest = new CustomerRequest();

        CustomerResponse<Customer> customerResponse = new CustomerResponse<>();
        Mockito.when(customerServiceMock.createCustomer( Mockito.any())).thenReturn(customer);
        Mockito.when(creationHelperMock.createResponse(customer)).thenReturn(customerResponse);

        // Crear instancia de CustomerApi
        CustomerApi customerApi = new CustomerApi(customerServiceMock, creationHelperMock);

        // Ejecutar método a prueba
        ResponseEntity<CustomerResponse<Customer>> response = customerApi.addCustomerInformation(customerRequest);

        // Verificar resultado
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(customerResponse, response.getBody());

    }

    // Método para convertir objetos Java a JSON
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
