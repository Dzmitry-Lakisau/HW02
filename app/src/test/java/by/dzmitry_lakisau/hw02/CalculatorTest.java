package by.dzmitry_lakisau.hw02;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.spy;

public class CalculatorTest {


    private ICalculator mCalculator;

    @Before
    public void setUp() {
        mCalculator = spy(Calculator.class);
    }

    @Test
    public void testAdd() {
        String result = mCalculator.add((float) -1 ,(float) 2.0);
        assertEquals(result, "1");

        result = mCalculator.add((float) 21 ,(float) 2.0);
        assertEquals(result, "23");

        result = mCalculator.add((float) -6.5 ,(float) -0.7);
        assertEquals(result, "-7.2");
    }

    @Test
    public void testMultiply() {
        String result = mCalculator.multiply((float) -1.3 ,(float) 2.0);
        assertEquals(result, "-2.6");

        result = mCalculator.multiply((float) -1.5 ,(float) 4.0);
        assertEquals(result, "-6");

        result = mCalculator.multiply((float) -1.3 ,(float) -2.0);
        assertEquals(result, "2.6");

        result = mCalculator.multiply((float) -1.5 ,(float) -2.0);
        assertEquals(result, "3");
    }
}