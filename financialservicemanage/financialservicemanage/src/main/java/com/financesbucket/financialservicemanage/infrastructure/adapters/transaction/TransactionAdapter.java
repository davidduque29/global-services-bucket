package com.financesbucket.financialservicemanage.infrastructure.adapters.transaction;

import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.transaction.transactionmodel.Transaction;
import com.financesbucket.financialservicemanage.domain.transaction.transactionport.TransactionGateway;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository.ProductCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.constants.TransactionConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.entity.TransactionEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.helper.TransactionModelConvertHelper;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.mapper.TransactionConverter;
import com.financesbucket.financialservicemanage.infrastructure.adapters.transaction.repository.TransactionCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransactionAdapter implements TransactionGateway {
    private ProductEntity productEntityId;
    private final TransactionCrudRepository transactionCrudRepository;
    private final ProductCrudRepository productCrudRepository;

    @Override
    public Transaction createTransaction(Transaction transactionModel) {
        validateAccountExistence(transactionModel);
        var transactionEntity = convertToTransactionEntity(transactionModel);
        transactionEntity.setFechaTransaccion(new Date());
        var userAccount = findUserAccount(transactionModel);
        validateTransactionType(transactionModel, userAccount);
        var savedTransaction = saveTransaction(transactionEntity);
        return TransactionConverter.convertEntityToTransaction(savedTransaction);
    }

    private void validateAccountExistence(Transaction transactionModel) {
        transactionModel.validTransaction();
        Optional<ProductEntity> optionalProductOrigin = productCrudRepository.findProductById(transactionModel.getCuentaId());
        if (!optionalProductOrigin.isPresent()) {
            throw new BussinessException(TransactionConstants.ERR_NOT_FOUND_STATUS
                    , TransactionConstants.ERR_ACCOUNT_NOT_FOUND_DESCRIPTION
                    , TransactionConstants.ERR_NOT_FOUND_CODE);
        }
    }

    private TransactionEntity convertToTransactionEntity(Transaction transactionModel) {
        TransactionModelConvertHelper transactionData = new TransactionModelConvertHelper();
        return transactionData.convertModelToTransactionEntity(transactionModel);
    }

    private ProductEntity findUserAccount(Transaction transactionModel) {
        Optional<ProductEntity> optionalProductOrigin = productCrudRepository.findProductById(transactionModel.getCuentaId());
        return optionalProductOrigin.orElseThrow(() -> new BussinessException(TransactionConstants.ERR_NOT_FOUND_STATUS
                , TransactionConstants.ERR_ACCOUNT_NOT_FOUND_DESCRIPTION
                , TransactionConstants.ERR_NOT_FOUND_CODE));
    }

    private void validateTransactionType(Transaction transaction, ProductEntity productOrigin) {
        Double monto = transaction.getMonto();
        if (TransactionConstants.TRANSACTION_TRANSFER.equals(transaction.getTipo())) {
            updateBalanceForTransfer(transaction, productOrigin, monto);
        } else if (TransactionConstants.TRANSACTION_CONSIGNMENT.equals(transaction.getTipo())) {
            updateBalanceForConsignation(productOrigin, monto);
        } else if (TransactionConstants.TRANSACTION_WITHDRAWAL.equals(transaction.getTipo())) {
            updateBalanceForWithdrawal(productOrigin, monto);
        }
    }

    private void updateBalanceForTransfer(Transaction transactionModel, ProductEntity productOrigin, Double monto) {
        if (productOrigin.getSaldo() < monto) {
            throw new BussinessException(TransactionConstants.ERR_INSUFFICIENT_MONEY_STATUS
                    , TransactionConstants.ERR_INSUFFICIENT_MONEY_DESCRIPTION
                    , TransactionConstants.ERR_FORBIDDEN_CODE);
        }
        productOrigin.setSaldo(productOrigin.getSaldo() - monto);
        Optional<ProductEntity> optionalProductDestination = productCrudRepository.findProductById(transactionModel.getCuentaId());
        ProductEntity productDestination = optionalProductDestination.orElseThrow(() -> new BussinessException("ERR_ACCOUNT_NOT_FOUND", "La cuenta de destino no existe en el sistema", "404"));
        productDestination.setSaldo(productDestination.getSaldo() + monto);
        productCrudRepository.save(productDestination);
    }

    private void updateBalanceForConsignation(ProductEntity productOrigin, Double monto) {
        productOrigin.setSaldo(productOrigin.getSaldo() + monto);
        productCrudRepository.save(productOrigin);
    }

    private void updateBalanceForWithdrawal(ProductEntity productOrigin, Double monto) {
        if (productOrigin.getSaldo() < monto) {
            throw new BussinessException(TransactionConstants.ERR_INSUFFICIENT_MONEY_STATUS
                    , TransactionConstants.ERR_INSUFFICIENT_MONEY_DESCRIPTION
                    , TransactionConstants.ERR_FORBIDDEN_CODE);
        }
        productCrudRepository.updateAccountBalanceById(productOrigin.getId(), monto);
    }

    private TransactionEntity saveTransaction(TransactionEntity transactionEntity) {
        return transactionCrudRepository.save(transactionEntity);
    }


    @Override
    public Optional<Transaction> updateTransactionId(Long transactionId, Transaction transactionInput) {
        return Optional.empty();
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return TransactionConverter.toTransactionList(transactionCrudRepository.findAll());
    }

    @Override
    public Optional<Transaction> findTransactionById(Long id) {
        return Optional.empty();
    }

    @Override
    public void deleteTransaction(Long id) {

    }
/*

    @Override
    public Optional<TransactionEntity> updateTransactionId(Long transactionId, TransactionEntity transactionInput) {
        return transactionCrudRepository.findById(transactionId).map(transaction -> {
            transaction.setTipo(transactionInput.getTipo());
            transaction.setMonto(transactionInput.getMonto());
            return transactionCrudRepository.save(transaction);
        });
    }
    @Override
    public List<TransactionEntity> findAllTransactions() {
        return transactionCrudRepository.findAll();
    }

    @Override
    public Optional<TransactionEntity> findTransactionById(Long id) {
        return transactionCrudRepository.findById(id);
    }

    @Override
    public void deleteTransaction(Long id) {
        transactionCrudRepository.deleteById(id);
    }*/
}
