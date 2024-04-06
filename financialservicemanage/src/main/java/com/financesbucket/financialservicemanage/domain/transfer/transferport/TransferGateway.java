package com.financesbucket.financialservicemanage.domain.transfer.transferport;

import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;

public interface TransferGateway {
    Transfer createTransfer(Transfer transferModel);
    Transfer findTransferById(Long id);

}
