package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transaction.response;

import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import lombok.Data;

@Data
public class TransactionResponse<T> {
    private T transaction;
    private StateMessage stateMessage;
}
