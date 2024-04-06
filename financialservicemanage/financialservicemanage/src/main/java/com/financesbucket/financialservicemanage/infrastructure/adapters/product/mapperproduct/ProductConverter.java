package com.financesbucket.financialservicemanage.infrastructure.adapters.product.mapperproduct;

import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;
import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.constants.ProductConstants;
import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    // Convertir ProductDto a ProductEntity
    public static ProductEntity convertProductToEntity(Product dto) {
        ProductEntity entity = new ProductEntity();
        entity.setTipoCuenta(convertElementToTipoCuenta(dto.getTipoCuenta()));
        entity.setNumeroCuenta(dto.getNumeroCuenta());
        entity.setEstado(convertElementToAccountState(dto.getEstado()));
        entity.setSaldo(dto.getSaldo().doubleValue());
        entity.setExentaGMF(dto.isExentaGMF());
        entity.setFechaCreacion(dto.getFechaCreacion());
        entity.setFechaModificacion(dto.getFechaModificacion());
        entity.setCliente(convertLongToEntity(dto.getClienteId()));
        return entity;
    }
    public static ProductEntity.TipoCuenta convertElementToTipoCuenta(String tipoCuentaString) {
        if (ProductConstants.SAVINGS_ACCOUNT_TYPE.equals(tipoCuentaString)) {
            return ProductEntity.TipoCuenta.CUENTA_DE_AHORROS;
        } else if (ProductConstants.CURRENT_ACCOUNT_TYPE.equals(tipoCuentaString)) {
            return ProductEntity.TipoCuenta.CUENTA_CORRIENTE;
        }
        // Manejar el caso en el que el tipo de cuenta no sea reconocido o sea nulo
        return null; // O lanzar una excepción, según requisitos
    }

    public static CustomerEntity convertLongToEntity(Long clientId) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(clientId);
        return customerEntity;
    }
    public static ProductEntity.EstadoCuenta convertElementToAccountState(String estadoCuenta) {
        if (ProductConstants.ACTIVE_ACCOUNT.equals(estadoCuenta)) {
            return ProductEntity.EstadoCuenta.ACTIVA;
        } else if (ProductConstants.INACTIVE_ACCOUNT.equals(estadoCuenta)) {
            return ProductEntity.EstadoCuenta.INACTIVA;
        } else if (ProductConstants.CANCELED_ACCOUNT.equals(estadoCuenta)) {
            return ProductEntity.EstadoCuenta.CANCELADA;
        }
        return null;
    }

    // Convertir ProductEntity a ProductDto
    public static Product convertEntityToProduct(ProductEntity entity) {
        return Product.builder()
                .tipoCuenta(entity.getTipoCuenta().name())
                .numeroCuenta(entity.getNumeroCuenta())
                .estado(entity.getEstado().name())
                .saldo(entity.getSaldo())
                .exentaGMF(entity.isExentaGMF())
                .fechaCreacion(entity.getFechaCreacion())
                .fechaModificacion(entity.getFechaModificacion())
                .clienteId(entity.getCliente().getId())
                .build();
    }
    public static Optional<ProductEntity> convertProductToEntity(Optional<Product> optionalDto) {
        return optionalDto.map(ProductConverter::convertProductToEntity);
    }

    public static Optional<Product> convertEntityToProduct(Optional<ProductEntity> optionalEntity) {
        return optionalEntity.flatMap(entity -> Optional.ofNullable(ProductConverter.convertEntityToProduct(entity)));
    }
    // Convertir una lista de ProductDto a una lista de ProductEntity
    public static List<ProductEntity> convertToEntityList(List<Product> dtos) {
        List<ProductEntity> entities = new ArrayList<>();
        for (Product dto : dtos) {
            entities.add(convertProductToEntity(dto));
        }
        return entities;
    }

    // Convertir una lista de ProductEntity a una lista de ProductDto
    public static List<Product> convertToProductList(List<ProductEntity> entities) {
        List<Product> dtos = new ArrayList<>();
        for (ProductEntity entity : entities) {
            dtos.add(convertEntityToProduct(entity));
        }
        return dtos;
    }
// Convertir una lista de Optional<Product> a una lista de Optional<ProductEntity>
    public static List<Optional<ProductEntity>> convertToEntityOptionalList(List<Optional<Product>> optionalDtos) {
        List<Optional<ProductEntity>> optionalEntities = new ArrayList<>();
        for (Optional<Product> optionalDto : optionalDtos) {
            optionalEntities.add(optionalDto.map(ProductConverter::convertProductToEntity));
        }
        return optionalEntities;
    }

    // Convertir una lista de Optional<ProductEntity> a una lista de Optional<Product>
    public static List<Optional<Product>> convertToDtoOptionalList(List<Optional<ProductEntity>> optionalEntities) {
        List<Optional<Product>> optionalDtos = new ArrayList<>();
        for (Optional<ProductEntity> optionalEntity : optionalEntities) {
            optionalDtos.add(optionalEntity.map(ProductConverter::convertEntityToProduct));
        }
        return optionalDtos;
    }
}
