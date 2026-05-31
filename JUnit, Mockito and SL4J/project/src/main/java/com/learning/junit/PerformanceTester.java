package com.learning.junit;

public class PerformanceTester {
    public String performTask() {
        for (int i = 0; i < 1_000_000; i++) {
            Math.sqrt(i);
        }
        return "Done";
    }
}
