import java.util.Arrays;
import java.util.List;

/**
 * Created by Ryan on 2/22/2016.
 */
public class OperandCheckerUtility {
    private static List<String> mPossibleRegisters = Arrays.asList("R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7");
    private static String genericErrorMessage = ": Operand is ill formed";
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
            return new Error(ErrorType.ILL_FORMED_OPERAND, in + genericErrorMessage);
        }

        //Length is <= 5 so, now we check for only letters.
        if(in.matches("[a-zA-Z]+")){
            return null;
        }
        return new Error(ErrorType.ILL_FORMED_OPERAND, in + genericErrorMessage);
    }

    public static Error isImmediateValue(String in){
        try{
            int parsed = Integer.parseInt(in);
            if(parsed >= 0) {
                return null;
            } else {
                return new Error(ErrorType.ILL_FORMED_OPERAND, in + ": not a valid Octet unsigned number");
            }
        } catch(NumberFormatException nfe){
            return new Error(ErrorType.ILL_FORMED_OPERAND, in + ": not a valid Octet unsigned number");
        }
    }

    public static Error isLabel(String in){
        if(in.matches("[a-zA-Z]+")){
            return null;
        }
        else{
            return new Error(ErrorType.ILL_FORMED_LABEL, in + ": Not a valid label type");
        }
    }


}
