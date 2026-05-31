package com.learning.junit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void testThrowException() {
        assertThrows(RuntimeException.class, () -> exceptionThrower.throwException());
    }
}
