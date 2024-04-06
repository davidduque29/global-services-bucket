package com.financesbucket.financialservicemanage.application.config;

import com.financesbucket.financialservicemanage.application.product.productusecase.UseCaseProduct;
import com.financesbucket.financialservicemanage.domain.product.productport.ProductGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProductConfig {
    @Primary
    @Bean
    public UseCaseProduct productUseCaseConfiguration(ProductGateway productGateway){
        return new UseCaseProduct(productGateway);
    }
}
