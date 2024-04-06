package com.financesbucket.financialservicemanage.infrastructure.adapters.product;

import com.financesbucket.financialservicemanage.domain.exceptions.BussinessException;
import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;
import com.financesbucket.financialservicemanage.domain.product.productport.ProductGateway;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.constants.ProductConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.mapperproduct.ProductConverter;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository.ProductCrudRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository.ProductQueryRepository;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.utils.ProductUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductAdapter implements ProductGateway {
    private final ProductCrudRepository productCrudRepository;
    private final ProductQueryRepository businessRules;

    @Override
    public Product createProduct(Product productModel) {
        validateCustomerExistence(productModel.getClienteId());
        validateBalance(productModel.getSaldo());

        ProductEntity product = convertModelToEntity(productModel);
        setAccountNumber(productModel, product);
        setDefaultValues(product);
        saveProduct(product);

        return convertToProduct(product);
    }

    private void validateCustomerExistence(Long clientId) {
        int numClientes = businessRules.countExistingClientById(clientId);
        if (numClientes == 0) {
            throw new BussinessException("ERROR_CUSTOMER_NOT_FOUND",
                    "El cliente especificado no existe en la base de datos.",
                    "404");
        }
    }

    private void validateBalance(double saldo) {
        if (saldo <= 0) {
            throw new BussinessException("ERROR_NEGATIVE_BALANCE",
                    "La cuenta de ahorros no puede tener un saldo menor a $0.",
                    "422");
        }
    }

    private ProductEntity convertModelToEntity(Product productModel) {
        return ProductConverter.convertProductToEntity(productModel);
    }

    private void setAccountNumber(Product productModel, ProductEntity productEntity) {
        String numeroCuenta;
        if (ProductConstants.SAVINGS_ACCOUNT_TYPE.equals(productModel.getTipoCuenta())) {
            numeroCuenta = ProductUtils.generateAccountNumber(false);
        } else {
            numeroCuenta = ProductUtils.generateAccountNumber(true);
        }
        productEntity.setNumeroCuenta(numeroCuenta);
    }

    private void setDefaultValues(ProductEntity productEntity) {
        productEntity.setEstado(ProductEntity.EstadoCuenta.ACTIVA);
        productEntity.setFechaCreacion(new Date());
        // Asignar cliente al producto
        CustomerEntity cliente = new CustomerEntity();
        cliente.setId(productEntity.getCliente().getId());
        productEntity.setCliente(cliente);
    }

    private void saveProduct(ProductEntity productEntity) {
        productCrudRepository.save(productEntity);
    }

    private Product convertToProduct(ProductEntity productEntity) {
        return ProductConverter.convertEntityToProduct(productEntity);
    }


    @Override
    public Optional<Product> updateProductId(Long productId, Product productModel) {


        return productCrudRepository.findById(productId).map(productEntity -> {
            // Verifica si el estado de la cuenta es "CANCELADA" y el saldo es igual a 0
            if (productModel.getSaldo() == 0) {
                // Actualiza los campos de la entidad con los valores proporcionados
                productEntity.setTipoCuenta(ProductConverter.convertElementToTipoCuenta(productModel.getTipoCuenta()));
                productEntity.setNumeroCuenta(productModel.getNumeroCuenta());
                productEntity.setEstado(ProductConverter.convertElementToAccountState(productModel.getEstado()));
                productEntity.setSaldo(productModel.getSaldo());
                productEntity.setExentaGMF(productModel.isExentaGMF());
                productEntity.setFechaCreacion(productModel.getFechaCreacion());
                productEntity.setFechaModificacion(productModel.getFechaModificacion());

                // Guarda la entidad actualizada en la base de datos
                return ProductConverter.convertEntityToProduct(productCrudRepository.save(productEntity));
            } else {
                // Si no se cumple la condición de cancelación, lanza una excepción BussinessException
                throw new BussinessException("Bad Request"
                        , "No se pudo cancelar la cuenta porque el saldo no es igual a $0"
                        , "400");
            }
        });
    }

    @Override
    public List<Product> findAllProducts() {
        return ProductConverter.convertToProductList(productCrudRepository.findAll());
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return ProductConverter.convertEntityToProduct(productCrudRepository.findById(id));
    }

    @Override
    public void deleteProduct(Long id) {
        productCrudRepository.delete(ProductEntity.builder()
                .id(id).build());
    }

}

