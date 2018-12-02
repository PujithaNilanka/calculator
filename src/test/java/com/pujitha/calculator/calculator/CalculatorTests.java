package com.pujitha.calculator.calculator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class
 * */
public class CalculatorTests {
    private Calculator calculator = new Calculator();

    /**
     * Test sum method
     * */
    @Test
    public void testSum() {
        assertEquals(5, calculator.sum(2, 3));
    }
}
