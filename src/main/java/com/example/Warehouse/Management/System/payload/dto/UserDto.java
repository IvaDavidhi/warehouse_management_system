package com.example.Warehouse.Management.System.payload.dto;

import com.example.Warehouse.Management.System.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private String username;

    private String email;

    private String password;

    private List<UserRole> role;


}
