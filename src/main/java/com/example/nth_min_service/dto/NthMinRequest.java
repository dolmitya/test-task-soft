package com.example.nth_min_service.dto;

import lombok.Data;

@Data
public class NthMinRequest {
    private String path;
    private int n;
}