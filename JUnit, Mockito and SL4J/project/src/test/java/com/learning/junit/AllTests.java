package com.learning.junit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({CalculatorTest.class, AssertionsTest.class, EvenCheckerTest.class})
public class AllTests {
}
