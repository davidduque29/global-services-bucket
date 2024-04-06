package com.financesbucket.financialservicemanage.domain.exceptions;

import lombok.Getter;

@Getter
public class BussinessException extends RuntimeException{
    // Exception - obligatorio
    // RuntimeException - No chequeada
    private final String status;
    private final String detail;
    private final String code;

    public BussinessException(String status, String detail, String code) {
        this.status = status;
        this.detail = detail;
        this.code = code;
    }
}
