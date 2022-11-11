package com.example.Warehouse.Management.System.repository;

import com.example.Warehouse.Management.System.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    //User findByUsername(String username);

    User  findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
