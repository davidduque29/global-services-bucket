package com.financesbucket.financialservicemanage.infrastructure.adapters.email.repositoryemail;

import com.financesbucket.financialservicemanage.infrastructure.adapters.email.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Aquí puedes agregar métodos adicionales según sea necesario
}