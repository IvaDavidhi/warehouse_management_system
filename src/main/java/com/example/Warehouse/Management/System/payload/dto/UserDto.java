package com.example.Warehouse.Management.System.payload.dto;

import com.example.Warehouse.Management.System.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {

    private String username;

    private String email;

    private String password;

    private Set<UserRole> role;


}
