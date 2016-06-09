import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Ryan on 1/21/2016.
 * Parses each file
 */
public class SyntaxChecker {
    private static final String TAG = "SyntaxChecker";
    private StringBuilder mStringBuilder = new StringBuilder();
    private int lineNumber = 0;
    private ArrayList<Error> mAllErrorsFound = new ArrayList<>();

    /**
     * Will parse a file looking for errors,then return the necessary information to generate a log
     * @param f
     * @return
     */
    public LogInfo parseFile(File f){
        InputStream inputStream = null;
        BufferedReader br = null;
        String currentLine;

        try {
            inputStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((currentLine = br.readLine()) != null) {
                checkCurrentLine(currentLine);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Read File Line By Line
        return new LogInfo(mStringBuilder, mAllErrorsFound);
    }

    /**
     * Checks the current line, if no blanks, removes comments, then adds it to the log file. After this, checks for
     * valid label, instruction, and operands. If errors are found, adds them to the error list.
     */
    private void checkCurrentLine(String currentLine){
        String onlyCodeString = null;
        String label = null;
        String instructionString = null;
        String operands = "";
        Instruction instruction;
        Scanner scanner;

        try {
            if (!checkForBlankLines(currentLine)) {
                onlyCodeString = removeComments(currentLine);
                if(!onlyCodeString.isEmpty()) {
                    addToLogFile(onlyCodeString);
                    scanner = new Scanner(onlyCodeString);
                    label = scanner.next();
                } else{
                    return;
                }
            } else {
                return;
            }
            if (label.contains(":")) {
                Error error = checkLabel(label);
                if (error == null) {
                    instructionString = scanner.next();
                } else {
                    logError(error);
                    return;
                }
            } else if(label.contains("END")){

            }
            else {
                instructionString = label;
            }

            if(instructionString == null){
                //is a label on a line by itself
                return;
            }
            instruction = isValidInstruction(instructionString);

            while (scanner.hasNext()) {
                operands += scanner.next();
            }
            //check for labels
            if (instruction.getType() == null) {
                Error instructionError = new Error(ErrorType.INVALID_INSTRUCTION, instructionString);
                logError(instructionError);
                //Was not a valid instruction, so log it
            } else {
                Error error = instruction.areOperandsValid(operands);
                if (error != null) {
                    logError(error);
                }
            }
        } catch(NoSuchElementException nse){
            return;
        }
    }



    private boolean checkForBlankLines(String currentLine){
        if(currentLine == null ) {
            return true;
        }
        if(currentLine.isEmpty()){
            return true;
        }

        return false;
    }

    private String removeComments(String currentLine){
        String codeOnlyString = currentLine;

        if(currentLine.contains(";")){
            int index = currentLine.indexOf(";");
            codeOnlyString = currentLine.substring(0,index);
        }

        return codeOnlyString;
    }

    private Instruction isValidInstruction(String instruction){
        return new Instruction(instruction);
    }

    private Error checkLabel(String potentialLabel){
        //It is a label, else it is not
        if(potentialLabel.endsWith(":")) {
            potentialLabel = potentialLabel.substring(0, potentialLabel.length()-1);
            if (potentialLabel.length() <= 5 && potentialLabel.matches("[a-zA-Z]+")) {
                LabelMatcher.addLabelThatIsAvailable(potentialLabel);
                return null;
            } else {
                return new Error(ErrorType.ILL_FORMED_LABEL, "");
            }
        }else {
            return new Error(ErrorType.ILL_FORMED_LABEL, "");
        }
    }

    private void addToLogFile(String toAdd){
        mStringBuilder.append(""+ ++lineNumber + ".  " + toAdd + "\n");
    }

    private void logError(Error error){
        mStringBuilder.append("    **error:" + error.toAdditionalInfoString() + "\n");
        mAllErrorsFound.add(error);
    }
}
