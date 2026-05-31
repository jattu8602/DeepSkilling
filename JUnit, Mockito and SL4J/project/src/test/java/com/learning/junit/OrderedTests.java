package com.learning.junit;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderedTests {

    private static StringBuilder log = new StringBuilder();

    @BeforeAll
    static void setup() {
        log.setLength(0);
    }

    @Test
    @Order(2)
    void testSecond() {
        log.append("second");
    }

    @Test
    @Order(1)
    void testFirst() {
        log.append("first");
    }

    @Test
    @Order(3)
    void testThird() {
        log.append("third");
        assertEquals("firstsecondthird", log.toString());
    }
}
