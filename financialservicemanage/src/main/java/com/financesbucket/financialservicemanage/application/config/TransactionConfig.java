package com.financesbucket.financialservicemanage.application.config;

import com.financesbucket.financialservicemanage.application.transaction.transactionusecase.UseCaseTransaction;
import com.financesbucket.financialservicemanage.domain.transaction.transactionport.TransactionGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TransactionConfig {
    @Primary
    @Bean
    public UseCaseTransaction transactionUseCaseConfiguration(TransactionGateway transactionGateway){
        return new UseCaseTransaction(transactionGateway);
    }
}
