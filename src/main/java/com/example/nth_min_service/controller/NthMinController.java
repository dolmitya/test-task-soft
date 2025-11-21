package com.example.nth_min_service.controller;

import com.example.nth_min_service.dto.NthMinRequest;
import com.example.nth_min_service.service.NthMinService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NthMinController {

    private final NthMinService service;

    @Operation(summary = "Возвращает N-ое минимальное число из XLSX файла")
    @PostMapping("/nth-min")
    public int nthMin(@RequestBody NthMinRequest request) {
        return service.findNth(request.getPath(), request.getN());
    }
}