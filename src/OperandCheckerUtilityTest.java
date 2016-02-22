import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/22/2016.
 */
public class OperandCheckerUtilityTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

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
}