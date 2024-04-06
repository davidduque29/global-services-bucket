package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequest {
    private Long idCuentaEnvio;
    private Long idCuentaRecepcion;
    private Double monto;
    private String descripcion;
}
