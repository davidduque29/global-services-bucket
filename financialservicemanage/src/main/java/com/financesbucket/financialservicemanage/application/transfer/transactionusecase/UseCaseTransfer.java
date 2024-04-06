package com.financesbucket.financialservicemanage.application.transfer.transactionusecase;

import com.financesbucket.financialservicemanage.application.transfer.transactionservices.TransferService;
import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;
import com.financesbucket.financialservicemanage.domain.transfer.transferport.TransferGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UseCaseTransfer implements TransferService {
    private final TransferGateway transferGateway;

    @Override
    public Transfer createTransfer(Transfer transferEntity) {
        return transferGateway.createTransfer(transferEntity);
    }

    @Override
    public Transfer findTransferById(Long id) {
        return transferGateway.findTransferById(id);
    }

}
