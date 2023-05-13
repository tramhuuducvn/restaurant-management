package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMathTest  {
    @Test
    public void divide_SixDividedByTwo_ReturnThree() {
        final int expected = 3;
        final int actual = MyMath.divide(6, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void divide_OneDividedByTwo_ReturnZero() {
        final int expected = 0;
        final int actual = MyMath.divide(1, 2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void divide_OneDividedByZero_ThrowsIllegalArgumentException() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()->MyMath.divide(1, 0));
        String expectedMessage = "Cannot divide by zero (0).";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void add_SixAddedByTwo_ReturnEight() {
        final int expected = 4;
        final int actual = MyMath.add(6, 2);
        Assertions.assertEquals(expected, actual);
    }
}