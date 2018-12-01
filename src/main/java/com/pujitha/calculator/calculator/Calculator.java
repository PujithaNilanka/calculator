package com.pujitha.calculator.calculator;

import org.springframework.stereotype.Service;

/**
 * Service implementation
 */
@Service
public class Calculator {
    int sum(int a, int b) {
        return a + b;
    }
}
