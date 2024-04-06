package com.financesbucket.financialservicemanage.application.transaction.transactionusecase;

import com.financesbucket.financialservicemanage.application.transaction.transactionservices.TransactionService;
import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.domain.transaction.transactionport.TransactionGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UseCaseTransaction implements TransactionService {
    private final TransactionGateway transactionGateway;

    @Override
    public Transaction createTransaction(Transaction transactionEntity) {
        return transactionGateway.createTransaction(transactionEntity);
    }

    @Override
    public Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput) {
        return transactionGateway.updateTransactionId(transactionId, transactionInput);
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionGateway.findAllTransactions();
    }

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return transactionGateway.findTransactionById(id);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionGateway.deleteTransaction(id);
    }
}
