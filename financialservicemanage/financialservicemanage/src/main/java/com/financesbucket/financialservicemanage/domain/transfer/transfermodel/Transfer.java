package com.financesbucket.financialservicemanage.domain.transfer.transfermodel;

import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.exceptions.ExceptionUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Transfer {
    private Long idCuentaEnvio;
    private Long idCuentaRecepcion;
    private Double monto;
    private Date fechaTransferencia;
    private String estado;
    private String numeroReferencia;
    private String descripcion;



    public void validTransfer() {
        this.isNotNullOrEmpty();
    }

    private void isNotNullOrEmpty() {
        if (this.idCuentaEnvio == null ||  this.idCuentaRecepcion == null || this.monto == 0) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }
}

