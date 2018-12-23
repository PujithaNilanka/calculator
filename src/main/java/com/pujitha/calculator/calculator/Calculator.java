package com.pujitha.calculator.calculator;

import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

/**
 * Service implementation
 */
@Service
public class Calculator {
    @Cacheable
    int sum(int a, int b) {
        return a + b;
    }
}
