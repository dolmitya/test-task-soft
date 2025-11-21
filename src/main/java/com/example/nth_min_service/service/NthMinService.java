package com.example.nth_min_service.service;

import com.example.nth_min_service.util.XlsxReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NthMinService {

    private final XlsxReader reader;

    public int findNth(String path, int n) {
        var values = reader.read(path);

        if (n < 1 || n > values.size()) {
            throw new IllegalArgumentException("Некорректное N");
        }

        return Quickselect.select(values, 0, values.size() - 1, n - 1);
    }
}
