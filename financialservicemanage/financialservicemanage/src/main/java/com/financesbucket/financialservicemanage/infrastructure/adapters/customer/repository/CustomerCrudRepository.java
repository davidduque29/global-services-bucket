package com.financesbucket.financialservicemanage.infrastructure.adapters.customer.repository;

import com.financesbucket.financialservicemanage.infrastructure.adapters.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerCrudRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findCustomerById(Long id);
}
