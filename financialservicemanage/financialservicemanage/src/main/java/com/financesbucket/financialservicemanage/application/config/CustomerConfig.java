package com.financesbucket.financialservicemanage.application.config;

import com.financesbucket.financialservicemanage.application.customer.usecasecustomer.UseCaseCustomer;
import com.financesbucket.financialservicemanage.domain.customer.customerport.CustomerGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CustomerConfig {
    @Primary
    @Bean
    public UseCaseCustomer customerUseCaseConfiguration(CustomerGateway customerGateway){
        return new UseCaseCustomer(customerGateway);
    }

}
