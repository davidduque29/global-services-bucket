package com.financesbucket.financialservicemanage.infrastructure.adapters.product.repository;

import com.financesbucket.financialservicemanage.infrastructure.adapters.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductCrudRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findProductById(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE productos SET saldo = saldo - :monto WHERE id_producto = :productId", nativeQuery = true)
    void updateAccountBalanceById(@Param("productId") Long productId, @Param("monto") Double monto);


}
