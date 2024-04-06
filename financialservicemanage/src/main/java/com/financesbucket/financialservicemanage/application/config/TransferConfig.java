package com.financesbucket.financialservicemanage.application.config;

import com.financesbucket.financialservicemanage.application.transfer.transactionusecase.UseCaseTransfer;
import com.financesbucket.financialservicemanage.domain.transfer.transferport.TransferGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TransferConfig {
    @Primary
    @Bean
    public UseCaseTransfer transferUseCaseConfiguration(TransferGateway transferGateway){
        return new UseCaseTransfer(transferGateway);
    }
}
