package com.financesbucket.financialservicemanage.infrastructure.rest.controller.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private Double saldo;
    private boolean exentaGMF;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Long clienteId;
}
