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
        Error error;
        Instruction instruction;

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,r2, r3");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,   r2,r3");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,  r2, r3");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r6,r2, r3");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,r2 , r3");
        assertNull(error);

    }
}