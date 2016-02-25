import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/22/2016.
 * Checks operands to determine if they are valid
 */
public class OperandCheckerUtilityTest {
    @Test
    public void testRegisters() throws Exception {
        Error actual;

        actual = OperandCheckerUtility.isSourceOrDestination("R0");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R1");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R2");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R3");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R4");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R5");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R6");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R7");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("R8");
        assertEquals(ErrorType.WRONG_OPERAND_TYPE, actual.getErrorType());
    }

    @Test
    public void testLabels() throws Exception {
        Error actual;

        actual = OperandCheckerUtility.isSourceOrDestination("LOOP");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("START");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("END");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("LOOP");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("A");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("BASE");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNC");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("HW");
        assertNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNC1");
        assertNotNull(actual);

        actual = OperandCheckerUtility.isSourceOrDestination("FUNCTION");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, actual.getErrorType());

        actual = OperandCheckerUtility.isSourceOrDestination("HELLOO");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, actual.getErrorType());
    }

    @Test
    public void testIsImmediateValue() throws Exception{
        Error actual;

        actual = OperandCheckerUtility.isImmediateValue("234234");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("134");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("1");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("324");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("0");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("99");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("29384");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("11");
        assertNull(actual);

        actual = OperandCheckerUtility.isImmediateValue("-1");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, actual.getErrorType());

        actual = OperandCheckerUtility.isImmediateValue("-575");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, actual.getErrorType());

        actual = OperandCheckerUtility.isImmediateValue("dgfd");
        assertEquals(ErrorType.WRONG_OPERAND_TYPE, actual.getErrorType());

        actual = OperandCheckerUtility.isImmediateValue("345g43");
        assertEquals(ErrorType.WRONG_OPERAND_TYPE, actual.getErrorType());
    }
}