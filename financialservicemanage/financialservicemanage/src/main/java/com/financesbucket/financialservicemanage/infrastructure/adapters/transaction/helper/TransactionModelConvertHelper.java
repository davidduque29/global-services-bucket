package com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.helper;

import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.constants.TransactionConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity.TransactionEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionModelConvertHelper {

    public TransactionEntity convertModelToTransactionEntity(Transaction transactionModel) {
        return TransactionEntity.builder()
                .tipo(convertStringToTipoTransaccion(transactionModel.getTipo()))
                .monto(transactionModel.getMonto())
                .fechaTransaccion(transactionModel.getFechaTransaccion())
                .cuenta(convertLongToEntity(transactionModel.getCuentaId()))
                .build();
    }

    private TransactionEntity.TipoTransaccion convertStringToTipoTransaccion(String tipoTransaccion) {
        if (TransactionConstants.TRANSACTION_CONSIGNMENT.equals(tipoTransaccion)) {
            return TransactionEntity.TipoTransaccion.CONSIGNACION;
        } else if (TransactionConstants.TRANSACTION_WITHDRAWAL.equals(tipoTransaccion)) {
            return TransactionEntity.TipoTransaccion.RETIRO;
        } else if (TransactionConstants.TRANSACTION_TRANSFER.equals(tipoTransaccion)) {
            return TransactionEntity.TipoTransaccion.TRANSFERENCIA;
        }
        // Manejar el caso en el que el tipo de cuenta no sea reconocido o sea nulo
        return null; // O lanzar una excepción  según requisitos
    }

    public ProductEntity convertLongToEntity(Long clientId) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(clientId);
        return productEntity;
    }
}

