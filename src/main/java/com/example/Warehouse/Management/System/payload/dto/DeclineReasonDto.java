package com.example.Warehouse.Management.System.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeclineReasonDto {
    private String id;
    private String title;
    private String reason;

}
