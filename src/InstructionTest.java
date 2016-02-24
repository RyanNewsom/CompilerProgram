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

        //ADD, SUB, DIV
        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,r2, r3");
        assertNull(error);

        instruction = new Instruction("SUB");
        error = instruction.areOperandsValid("R4,   r2,r3");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r6,r2, r3");
        assertNull(error);

        instruction = new Instruction("DIV");
        error = instruction.areOperandsValid("r1,r2 , r3");
        assertNull(error);

        instruction = new Instruction("DIV");
        error = instruction.areOperandsValid("r1,  r2,");
        assertEquals(ErrorType.TOO_FEW_OPERANDS, error.getmErrorType());

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,  r2, r3, r4, r5, r6");
        assertEquals(ErrorType.TOO_MANY_OPERANDS, error.getmErrorType());

        instruction = new Instruction("SUB");
        error = instruction.areOperandsValid("r1, r3fgh, r4");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, error.getmErrorType());

        instruction = new Instruction("ADDdD");
        error = instruction.areOperandsValid("r1,  r2, r3, r4");
        assertNull(error);

        instruction = new Instruction("ADD");
        error = instruction.areOperandsValid("r1,  r2, r3, r4, r7");
        assertEquals(ErrorType.TOO_MANY_OPERANDS, error.getmErrorType());

        //MOVE
        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,r2");
        assertNull(error);

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("X,r2");
        assertNull(error);

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,ycvcv");
        assertNull(error);

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,rd2");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, error.getmErrorType());

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,r2,r3");
        assertEquals(ErrorType.TOO_MANY_OPERANDS, error.getmErrorType());

        //MOVEI
        instruction = new Instruction("MOVEI");
        error = instruction.areOperandsValid("567,r2");
        assertNull(error);

        //INC, DEC

        //BEQ,BLT,BGT

        //BR

        //END
    }
}