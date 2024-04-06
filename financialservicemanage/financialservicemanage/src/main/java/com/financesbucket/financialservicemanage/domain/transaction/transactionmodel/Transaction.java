package com.financesbucket.financialservicemanage.domain.transaction.transactionmodel;

import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.exceptions.ExceptionUtil;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder(toBuilder = true)
public class Transaction {
    private String tipo; // Tipo de transacción: Consignación, Retiro, Transferencia
    private double monto;
    private Date fechaTransaccion;
    private Long cuentaId;


    public void validTransaction() {
        this.isNotNullOrEmpty();
    }

    private void isNotNullOrEmpty() {
        if (this.tipo == null ||  this.tipo.isBlank() || this.monto == 0) {
            throw new BussinessException(ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_STATUS
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_DETAIL
                    , ExceptionUtil.LOAD_NOT_ACCEPTABLE_EXCEPTION_CODE);

        }

    }
}

