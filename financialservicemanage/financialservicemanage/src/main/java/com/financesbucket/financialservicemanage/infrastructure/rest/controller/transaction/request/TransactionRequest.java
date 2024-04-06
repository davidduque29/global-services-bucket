package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.request;

import lombok.*;

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
