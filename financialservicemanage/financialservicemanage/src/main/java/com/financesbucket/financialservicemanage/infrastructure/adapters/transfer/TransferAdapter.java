package com.financesbucket.financialservicemanage.infrastructure.adapters.transfer;

import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;
import com.financesbucket.financialservicemanage.domain.transfer.transfermodel.Transfer;
import com.financesbucket.financialservicemanage.domain.transfer.transferport.TransferGateway;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.constants.ProductConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.mapperproduct.ProductConverter;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository.ProductCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.constants.TransactionConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity.TransactionEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.repository.TransactionCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.constants.TransferConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.entity.TransferEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.mapper.TransferConverter;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.repository.TransferCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transfer.utils.TransferUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransferAdapter implements TransferGateway {
    private final TransferCrudRepository transferCrudRepository;
    private final TransactionCrudRepository transactionCrudRepository;
    private final ProductCrudRepository productCrudRepository;

    @Override
    public Transfer createTransfer(Transfer transferModel) {
        var transferEntity = convertToTransferEntity(transferModel);

        var transaction = TransactionEntity.builder()
                .tipo(TransactionEntity.TipoTransaccion
                        .valueOf(TransactionConstants.TRANSACTION_TRANSFER))
                .monto(transferModel.getMonto())
                .fechaTransaccion(new Date())
                .cuenta(ProductEntity.builder()
                        .id(transferModel.getIdCuentaEnvio()).build())
                .build();
        var account = ProductConverter.convertEntityToProduct(productCrudRepository
                .findProductById(transferModel.getIdCuentaEnvio()));
        // Se hace la operacion de restarle al saldo de la cuenta de envio, el valor que va a transferir
        // a la cuenta de destino y despues sumarle a la cuenta de destino ese monto


        //setReferenceNumber(account, transferEntity);
        var savedTransaction = saveTransaction(transaction);
        transferEntity.setNumeroReferencia("TRF-" + account.get().getNumeroCuenta());
        transferEntity.setEstado(TransferEntity
                .EstadoTransferencia.COMPLETADA);
        var savedTranfer = saveTransfer(transferEntity);
        return TransferConverter.convertEntityToTransfer(savedTranfer);
    }

    private TransferEntity convertToTransferEntity(Transfer transfer) {
        transfer.setEstado(TransferConstants.TRANSFER_PENDING);
        return TransferConverter.convertTransferToEntity(transfer);
    }

    private TransferEntity saveTransfer(TransferEntity transferEntity) {
        transferEntity.setFechaTransferencia(new Date());
        return transferCrudRepository.save(transferEntity);
    }

    private TransactionEntity saveTransaction(TransactionEntity transactionEntity) {
        return transactionCrudRepository.save(transactionEntity);
    }

    private void setReferenceNumber(Optional<Product> productModel, TransferEntity transferEntity) {
        String numeroReferencia;
        if (productModel.get().getTipoCuenta().equals(ProductConstants.SAVINGS_ACCOUNT_TYPE)) {
            numeroReferencia = TransferUtils.generateReferenceNumber(false);
        } else {
            numeroReferencia = TransferUtils.generateReferenceNumber(true);
        }
        transferEntity.setNumeroReferencia(numeroReferencia);
    }

    @Override
    public Transfer findTransferById(Long id) {
        return null;
    }
}
