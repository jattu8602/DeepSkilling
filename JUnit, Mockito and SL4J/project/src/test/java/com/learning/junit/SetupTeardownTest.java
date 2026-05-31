package com.learning.junit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SetupTeardownTest {

    private int counter;

    @BeforeEach
    void setUp() {
        counter = 0;
    }

    @AfterEach
    void tearDown() {
        counter = -1;
    }

    @Test
    void testIncrement() {
        counter++;
        assertEquals(1, counter);
    }

    @Test
    void testDecrement() {
        counter--;
        assertEquals(-1, counter);
    }
}
