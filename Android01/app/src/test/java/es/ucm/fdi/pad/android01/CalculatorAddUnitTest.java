package es.ucm.fdi.pad.android01;

import org.junit.Test;

import static org.junit.Assert.*;
public class CalculatorAddUnitTest {

    @Test
    public void checkCalculatorAdd(){
        double res1 = CalculatorClass.addNumbers(2.0, 3.0);
        assertEquals(5.0, res1, 1e-9);

        double res2 = CalculatorClass.addNumbers(10.0, -4.0);
        assertEquals(6.0, res2, 1e-9);

        double res3 = CalculatorClass.addNumbers(1.5, 2.25);
        assertEquals(3.75, res3, 1e-9);

        double res4 = CalculatorClass.addNumbers(0.3333333, 0.6666667);
        assertEquals(1.0, res4, 1e-7);
    }
}
