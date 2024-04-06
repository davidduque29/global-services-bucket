package com.financesbucket.financialservicemanage.infrastructure.rest.controller.customer.response;

import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import lombok.Data;

@Data
public class CustomerResponse<T> {
    private T client;
    private StateMessage stateMessage;
}
