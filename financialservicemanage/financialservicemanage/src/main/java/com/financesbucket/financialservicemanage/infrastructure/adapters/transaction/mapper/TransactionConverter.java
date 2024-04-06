package com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.mapper;


import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity.TransactionEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransactionConverter {
    public static Transaction convertEntityToTransaction(TransactionEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null");
        }
        return Transaction.builder()
                .tipo(entity.getTipo().name())
                .monto(entity.getMonto())
                .fechaTransaccion(entity.getFechaTransaccion())
                .cuentaId(entity.getCuenta().getId())
                .build();
    }

    public static TransactionEntity convertTransactionToEntity(Transaction dto) {
        if (dto == null) {
            throw new IllegalArgumentException("DTO cannot be null");
        }
        TransactionEntity entity = new TransactionEntity();
        entity.setTipo(TransactionEntity.TipoTransaccion.valueOf(dto.getTipo()));
        entity.setMonto(dto.getMonto());
        entity.setFechaTransaccion(dto.getFechaTransaccion());
        return entity;
    }

    public static List<Transaction> toTransactionList(List<TransactionEntity> entities) {
        Objects.requireNonNull(entities, "Entity list cannot be null");
        return entities.stream()
                .map(TransactionConverter::convertEntityToTransaction)
                .toList();
    }

    public static List<TransactionEntity> toEntityList(List<Transaction> dtos) {
        Objects.requireNonNull(dtos, "DTO list cannot be null");
        return dtos.stream()
                .map(TransactionConverter::convertTransactionToEntity)
                .toList();
    }
}