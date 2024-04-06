package com.financesbucket.financialservicemanage.infrastructure.rest.controller.transfer.response;

import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import lombok.Data;

@Data
public class TransferResponse<T> {
    private T transfer;
    private StateMessage stateMessage;
}
