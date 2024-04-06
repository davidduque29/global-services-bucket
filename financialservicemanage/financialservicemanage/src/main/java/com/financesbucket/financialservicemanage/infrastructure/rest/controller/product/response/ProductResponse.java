package com.financesbucket.financialservicemanage.infrastructure.rest.controller.product.response;

import com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage.StateMessage;
import lombok.Data;

@Data
public class ProductResponse<T> {
    private T product;
    private StateMessage stateMessage;
}
