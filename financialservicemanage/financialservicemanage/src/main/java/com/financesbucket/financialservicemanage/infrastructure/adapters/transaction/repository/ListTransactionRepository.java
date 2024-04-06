package com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.repository;

import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListTransactionRepository extends JpaRepository<CustomerEntity, Long> {

    @Query(value = "SELECT p FROM TransactionEntity p WHERE p.id = ?1")
    TransactionEntity findCountIdByCountId(Long id);


}
