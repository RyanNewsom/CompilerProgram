import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ryan on 2/22/2016.
 * An instruction is a command given in MAL that initiates something.
 */
public class Instruction {
    private InstructionType mType;
    private int mExpectedOperandAmount;
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

    public Error areOperandsValid(String operands){
        Scanner operandScanner = new Scanner(operands);
        List<String> operandsList = new ArrayList<>();
        Error error;
        operandScanner.useDelimiter(",");

        //Populates all the operands
        while(operandScanner.hasNext()){
            String next = operandScanner.next();
            operandsList.add(next);
        }

        if(mType == InstructionType.ADD || mType == InstructionType.SUB || mType == InstructionType.DIV ||
                mType == InstructionType.MUL){
            mExpectedOperandAmount = 3;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());

            if(operandAmountError!= null){
                return operandAmountError;
            }

            //Check each operand
            for(int i = 0; i < operandsList.size(); i++){
                error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(i));
                if(error != null){
                    return error;
                }
            }

        } else if(mType == InstructionType.INC || mType == InstructionType.DEC){
            mExpectedOperandAmount = 1;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());
            if(operandAmountError != null){
                return operandAmountError;
            }
            error = OperandCheckerUtility.isLabel(operandsList.get(0));
            if(error != null){
                return error;
            }

        } else if(mType == InstructionType.BEQ || mType == InstructionType.BGT || mType == InstructionType.BLT ){
            mExpectedOperandAmount = 3;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());
            if(operandAmountError!= null){
                return operandAmountError;
            }
            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(0));
            if(error != null){
                return error;
            }
            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(1));
            if(error != null){
                return error;
            }
            error = OperandCheckerUtility.isLabel(operandsList.get(2));
            if(error != null){
                return error;
            }

            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(1));
            if(error != null){
                return error;
            }
            error = OperandCheckerUtility.isLabel(operandsList.get(2));
            if(error != null){
                return error;
            }

        } else if(mType == InstructionType.BR){
            mExpectedOperandAmount = 1;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());
            if(operandAmountError!= null){
                return operandAmountError;
            }
            error = OperandCheckerUtility.isLabel(operandsList.get(0));
            if(error != null){
                return error;
            }

        } else if(mType == InstructionType.MOVE){
            mExpectedOperandAmount = 2;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());
            if(operandAmountError!= null){
                return operandAmountError;
            }
            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(0));
            if(error != null){
                return error;
            }
            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(1));
            if(error != null){
                return error;
            }

        } else if(mType == InstructionType.MOVEI){
            mExpectedOperandAmount = 2;
            Error operandAmountError = checkOperandAmount(mExpectedOperandAmount, operandsList.size());
            if(operandAmountError!= null){
                return operandAmountError;
            }
            error = OperandCheckerUtility.isImmediateValue(operandsList.get(0));
            if(error != null){
                return error;
            }
            error = OperandCheckerUtility.isSourceOrDestination(operandsList.get(1));
            if(error != null){
                return error;
            }
        }
        return null;
    }

    /**
     *
     * @param expected - number of operands expected
     * @param actual - actual number of operands
     * @return 0 - good, 1 - actual is greater, -1 - actual is less then expected
     */
    private Error checkOperandAmount(int expected, int actual){
        if(expected == actual){
            return null;
        } else if(actual > expected){
            return new Error(ErrorType.TOO_MANY_OPERANDS, "");
        } else if(actual < expected){
            return new Error(ErrorType.TOO_FEW_OPERANDS, "");
        }
        return null;
    }
}
