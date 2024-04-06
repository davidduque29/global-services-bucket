package com.financesbucket.financialservicemanage.application.transfer.transactionservices;

import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;

public interface TransferService {
    Transfer createTransfer(Transfer transferEntity);
    Transfer findTransferById(Long id);

}
