import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/22/2016.
 */
public class OperandCheckerUtilityTest {
    @Test
    public void testRegisters() throws Exception {
        boolean actual;
        boolean expected = true;

        actual = OperandCheckerUtility.isSourceOrDestination("R0");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R1");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R2");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R3");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R4");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R5");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R6");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R7");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R8");
        assertEquals(false, actual);
    }

    @Test
    public void testLabels() throws Exception {
        boolean actual;
        boolean expected = true;

        actual = OperandCheckerUtility.isSourceOrDestination("LOOP");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("START");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("END");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("LOOP");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("A");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("BASE");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNC");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("HW");
        assertEquals(expected, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNC1");
        assertEquals(false, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNCTION");
        assertEquals(false, actual);

        actual = OperandCheckerUtility.isSourceOrDestination("HELLOO");
        assertEquals(false, actual);
    }

    @Test
    public void testIsImmediateValue() throws Exception{
        boolean expectedTrue = true;
        boolean expectedFalse = false;
        boolean actual;

        actual = OperandCheckerUtility.isImmediateValue("234234");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("134");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("1");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("324");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("0");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("99");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("29384");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("11");
        assertEquals(expectedTrue, actual);

        actual = OperandCheckerUtility.isImmediateValue("-1");
        assertEquals(expectedFalse, actual);

        actual = OperandCheckerUtility.isImmediateValue("-0");
        assertEquals(expectedFalse, actual);
    }
}