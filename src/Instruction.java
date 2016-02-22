import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ryan on 2/22/2016.
 * An instruction is a command given in MAL that initiates something.
 */
public class Instruction {
    private InstructionType mType;
    private List<InstructionType> mAllPossibleTypes = Arrays.asList(InstructionType.ADD, InstructionType.SUB,
            InstructionType.MUL, InstructionType.DIV, InstructionType.INC, InstructionType.DEC, InstructionType.BR,
            InstructionType.BEQ, InstructionType.BGT, InstructionType.BLT, InstructionType.MOVE, InstructionType.MOVEI);

    /**
     * Constructor
     * Checks to see if it can match the instruction passed in with one of the available instructions for the instruction
     * set. If there's no match found, the type will be null.
     * @param instruction -
     */
    public Instruction(String instruction){
        instruction = instruction.toUpperCase();
        for(int i = 0; i < mAllPossibleTypes.size(); i++){
            InstructionType type = mAllPossibleTypes.get(i);
            if(type.toString().equals(instruction)){
                mType = type;
            }
        }
    }

    /**
     *
     * @return - The instruction type, if NULL, a valid instruction was not given
     */
    public InstructionType getType() {
        return mType;
    }

    public void areOperandsValid(String operands){
        Scanner operandScanner = new Scanner(operands);
        String first;
        String second;
        String third;

        if(mType == InstructionType.ADD || mType == InstructionType.SUB || mType == InstructionType.DIV ||
                mType == InstructionType.MUL){

        } else if(mType == InstructionType.INC || mType == InstructionType.DEC){

        } else if(mType == InstructionType.BEQ || mType == InstructionType.BGT || mType == InstructionType.BLT ){

        } else if(mType == InstructionType.BR){

        } else if(mType == InstructionType.MOVE){

        } else if(mType == InstructionType.MOVEI){

        }
    }
}
