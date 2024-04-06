package com.financesbucket.financialservicemanage.infrastructure.rest.controller.controllermessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class StateMessage {
    int statusCode;
    String status;
    String title;
    String detail;

}
