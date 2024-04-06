package com.financesbucket.financialservicemanage.application.product.productservice;

import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Optional<Product> updateProductId(Long productId, Product product);
    List<Product> findAllProducts();
    Optional<Product> findProductById(Long id);
    void deleteProduct(Long id);
}
