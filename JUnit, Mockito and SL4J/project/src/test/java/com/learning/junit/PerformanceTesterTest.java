package com.learning.junit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testPerformTaskWithinTimeout() {
        String result = performanceTester.performTask();
        assertEquals("Done", result);
    }
}
