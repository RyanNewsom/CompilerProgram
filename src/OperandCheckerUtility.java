import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan on 2/21/2016.
 * Checks a string of operands and what it's expected value is
 */
public class OperandCheckerUtility {
    private static List<String> mPossibleRegisters = Arrays.asList("R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7");
    private OperandCheckerUtility() {
        //Utility class
    }

    /**
     * Determines whether an operand is a source or destination
     * @param operand -
     * @return - null if there's no error
     */
    public static Error isSourceOrDestination(String operand){
        for(int i = 0; i < mPossibleRegisters.size(); i++){
            String register = mPossibleRegisters.get(i);
            if(operand.equalsIgnoreCase(register)){
                return null;
            }
        }

        //Not a register, is it a memory location? Must be letters only, max length of 5.
        if(operand.length() > 5){
            return new Error(ErrorType.ILL_FORMED_OPERAND, operand + "- Operand is too long, max length is 5 characters");
        }

        //Length is <= 5 so, now we check for only letters.
        if(operand.matches("[a-zA-Z]+")){
            return null;
        }
        return new Error(ErrorType.WRONG_OPERAND_TYPE, operand + "- Operand can only be memory locations(R0-R7 OR named location(A-Z))");
    }

    /**
     * Determines whether an operand is an immediate value
     * @param operand -
     * @return - null if there's no error
     */
    public static Error isImmediateValue(String operand){
        try{
            int parsed = Integer.parseInt(operand);
            if(parsed >= 0) {
                return null;
            } else {
                return new Error(ErrorType.ILL_FORMED_OPERAND, operand + "- Negative numbers are not allowed");
            }
        } catch(NumberFormatException nfe){
            return new Error(ErrorType.WRONG_OPERAND_TYPE, operand + "- Not a valid Octet unsigned number");
        }
    }

    /**
     * Determines whether an operand is a a label
     * @param operand -
     * @return - null if there's no error
     */
    public static Error isLabel(String operand){
        if(operand.length() > 5){
            return new Error(ErrorType.ILL_FORMED_LABEL, operand + "- Label length can not be > 5");
        }
        if(operand.matches("[a-zA-Z]+")){
            LabelMatcher.addLabelThatWasBranchedTo(operand);

            return null;
        }
        else{return new Error(ErrorType.ILL_FORMED_LABEL, operand + "- Not a valid label type can only contain letters A-Z");
        }
    }
}
