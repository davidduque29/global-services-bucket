package com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.repository;

import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    @Query(value = "SELECT p FROM TransferEntity p WHERE p.id = ?1")
    TransferEntity findCountIdByCountId(Long id);


}
