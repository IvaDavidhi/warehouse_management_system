package com.example.Warehouse.Management.System.payload.dto;

import lombok.Data;

@Data
public class PasswordDto {
    private String oldPassword;

    private String newPassword;
}
