package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.request;

import lombok.*;

import java.util.Date;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoElectronico;
    private Date fechaNacimiento;
    private Date fechaModificacion;
}
