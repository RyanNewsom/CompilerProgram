import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/23/2016.
 */
public class InstructionTest {

    @Test
    public void testGetType() throws Exception {

    }

    @Test
    public void testAreOperandsValid() throws Exception {
        Instruction instruction = new Instruction("ADD");
        instruction.areOperandsValid("r1,r2, r3");
    }
}