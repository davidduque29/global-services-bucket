package com.financesbucket.financialservicemanage.application.transaction.transactionservices;

import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transactionEntity);
    Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput);
    List<Transaction> findAllTransactions();
    Optional<Transaction> findTransactionById(Long id);
    void deleteTransaction(Long id);
}
