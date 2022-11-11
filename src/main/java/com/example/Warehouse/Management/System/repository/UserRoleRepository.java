package com.example.Warehouse.Management.System.repository;

import com.example.Warehouse.Management.System.model.UserRole;
import com.example.Warehouse.Management.System.model.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    Optional<UserRole> findByName(RoleEnum name);
}
