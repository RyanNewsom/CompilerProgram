import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan on 2/22/2016.
 */
public class OperandCheckerUtility {
    private static List<String> mPossibleRegisters = Arrays.asList("R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7");
    private OperandCheckerUtility() {
        //Utility class
    }

    public static Error isSourceOrDestination(String in){
        for(int i = 0; i < mPossibleRegisters.size(); i++){
            String register = mPossibleRegisters.get(i);
            if(in.equalsIgnoreCase(register)){
                return null;
            }
        }

        //Not a register, is it a memory location? Must be letters only, max length of 5.
        if(in.length() > 5){
            return new Error(ErrorType.ILL_FORMED_OPERAND, in + "- Operand is too long, max length is 5 characters");
        }

        //Length is <= 5 so, now we check for only letters.
        if(in.matches("[a-zA-Z]+")){
            return null;
        }
        return new Error(ErrorType.ILL_FORMED_OPERAND, in + "- Operand can only be memory locations(R0-R7 OR named location(A-Z))");
    }

    public static Error isImmediateValue(String in){
        try{
            int parsed = Integer.parseInt(in);
            if(parsed >= 0) {
                return null;
            } else {
                return new Error(ErrorType.ILL_FORMED_OPERAND, in + "- Negative numbers are not allowed");
            }
        } catch(NumberFormatException nfe){
            return new Error(ErrorType.ILL_FORMED_OPERAND, in + "- Not a valid Octet unsigned number");
        }
    }

    public static Error isLabel(String in){
        if(in.length() > 5){
            return new Error(ErrorType.ILL_FORMED_LABEL, in + "- Not a valid label type, length can not be > 5");
        }
        if(in.matches("[a-zA-Z]+")){
            return null;
        }
        else{return new Error(ErrorType.ILL_FORMED_LABEL, in + "- Not a valid label type can only contain letters A-Z");
        }
    }


}
