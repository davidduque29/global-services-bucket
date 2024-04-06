package com.financesbucket.financialservicemanage.domain.transaction.transactionport;

import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionGateway {
    Transaction createTransaction(Transaction transactionModel);
    Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput);
    List<Transaction> findAllTransactions();
    Optional<Transaction> findTransactionById(Long id);
    void deleteTransaction(Long id);
}
