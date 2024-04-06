package com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.repository;

import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransferCrudRepository extends JpaRepository<TransferEntity, Long> {
    Optional<TransferEntity> findTransferById(Long id);
}
