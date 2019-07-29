package Suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.CalculationTest;
import tests.SavingCalculatorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculationTest.class,
        SavingCalculatorTest.class
})
public class defaultSuite {
}
