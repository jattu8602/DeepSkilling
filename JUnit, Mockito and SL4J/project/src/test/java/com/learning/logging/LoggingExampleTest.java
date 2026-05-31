package com.learning.logging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LoggingExampleTest {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExampleTest.class);

    @Test
    void testLogging() {
        logger.error("Test error message");
        logger.warn("Test warning message");
        logger.info("Test info message");
        logger.debug("Test debug message");
        logger.info("Parameterized: name={}, value={}", "test", 123);
    }
}
