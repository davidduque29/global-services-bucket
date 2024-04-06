package com.financesbucket.financialservicemanage.application.product.productusecase;


import com.financesbucket.financialservicemanage.application.product.productservice.ProductService;
import com.financesbucket.financialservicemanage.domain.product.productmodel.Product;
import com.financesbucket.financialservicemanage.domain.product.productport.ProductGateway;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UseCaseProduct implements ProductService {
    private final ProductGateway productGateway;

    @Override
    public Product createProduct(Product product) {
        return productGateway.createProduct(product);
    }

    @Override
    public Optional<Product> updateProductId(Long productId, Product product) {
        return productGateway.updateProductId(productId,product);
    }

    @Override
    public List<Product> findAllProducts() {
        return productGateway.findAllProducts();
    }

    @Override
    public Optional<Product> findProductById(Long id) {
        return productGateway.findProductById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productGateway.deleteProduct(id);
    }
}
