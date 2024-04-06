package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.request;

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
public class TransactionRequest {
    private String tipo;
    private double monto;
    private Date fechaTransaccion;
    private Long cuentaId;
}
