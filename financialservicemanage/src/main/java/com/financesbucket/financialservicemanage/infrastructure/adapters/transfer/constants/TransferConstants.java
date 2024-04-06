package com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransferConstants {
    public static final String TRANSFER_COMPLETE = "COMPLETADA";
    public static final String TRANSFER_PENDING  = "PENDIENTE";
    public static final String TRANSFER_CANCELLED = "CANCELADA";
}