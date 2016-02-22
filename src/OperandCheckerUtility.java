import com.sun.media.sound.InvalidFormatException;

import java.lang.reflect.Array;
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

    public static boolean isSourceOrDestination(String in){
        for(int i = 0; i < mPossibleRegisters.size(); i++){
            String register = mPossibleRegisters.get(i);
            if(in.equalsIgnoreCase(register)){
                return true;
            }
        }

        //Not a register, is it a memory location? Must be letters only, max length of 5.
        if(in.length() > 5){
            return false;
        }

        //Length is <= 5 so, now we check for only letters.
        return in.matches("[a-zA-Z]+");
    }

    public static boolean isImmediateValue(String in){
        try{

            Integer.parseUnsignedInt(in);
            return true;
        } catch(NumberFormatException nfe){
            return false;
        }
    }


}
