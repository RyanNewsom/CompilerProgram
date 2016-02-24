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

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,rd2");
        assertEquals(ErrorType.ILL_FORMED_OPERAND, error.getmErrorType());

        instruction = new Instruction("MOVE");
        error = instruction.areOperandsValid("r1,r2,r3");
        assertEquals(ErrorType.TOO_MANY_OPERANDS, error.getmErrorType());

        //INC, DEC
        instruction = new Instruction("INC");
        error = instruction.areOperandsValid("x");
        assertNull(error);

        instruction = new Instruction("INC");
        error = instruction.areOperandsValid("r5");
        assertNull(error);

        instruction = new Instruction("DEC");
        error = instruction.areOperandsValid("r0");
        assertNull(error);

        instruction = new Instruction("DEC");
        error = instruction.areOperandsValid("r0,r1");
        assertEquals(ErrorType.TOO_MANY_OPERANDS, error.getmErrorType());

        instruction = new Instruction("DEC");
        error = instruction.areOperandsValid("");
        assertEquals(ErrorType.TOO_FEW_OPERANDS, error.getmErrorType());

        //BEQ,BLT,BGT
        instruction = new Instruction("BEQ");
        error = instruction.areOperandsValid("R1, R2, CONT");
        assertNull(error);

        //BR
        instruction = new Instruction("BR");
        error = instruction.areOperandsValid("LOOP");
        assertNull(error);

        //END
    }
}