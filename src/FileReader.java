import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Ryan on 1/24/2016.
 * Parses each file
 */
public class FileReader {
    private static final String TAG = "FileReader";
    private StringBuilder mStringBuilder = new StringBuilder();
    private ArrayList mFoundLabels = new ArrayList();
    private ArrayList mBranchedToLabels = new ArrayList();
    private int lineNumber = 0;
    private ArrayList<Error> mAllErrorsFound = new ArrayList<>();
    //Take in file.
    //1)Check for blank lines & comments, if found then remove, add remaining to line to be scanned
    //2)Scan line for label

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
     * Checks the current line, if no blanks, removes comments, then adds it to the log file. After this, checks for label
     *
     * @return
     */
    private void checkCurrentLine(String currentLine){
        String onlyCodeString = null;
        String label = null;
        String instructionString = null;
        String operands = "";
        Instruction instruction;
        Scanner scanner;

        if(!checkForBlankLines(currentLine)) {
            onlyCodeString = removeComments(currentLine);
            addToLogFile(onlyCodeString);
            scanner = new Scanner(currentLine);
            label = scanner.next();
        } else{
            return;
        }
        if(label.contains(":")) {
            Error error = checkLabel(label);
            if (error == null) {
                instructionString = scanner.next();
            } else{
                logError(error);
                return;
            }
        }else{
            instructionString = label;
        }

        instruction = isValidInstruction(instructionString);

        while(scanner.hasNext()){
            operands += scanner.next();
        }
        //check for labels
        if(instruction.getType() == null){
            Error instructionError = new Error(ErrorType.INVALID_INSTRUCTION, instructionString);
            logError(instructionError);
            //Was not a valid instruction, so log it
        } else{
            Error error = instruction.areOperandsValid(operands);
            if(error != null){
                logError(error);
            }
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
            potentialLabel = potentialLabel.substring(0, potentialLabel.length() - 1);
            if (potentialLabel.length() <= 5 && potentialLabel.matches("[a-zA-Z]+")) {
                mFoundLabels.add(potentialLabel);
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
        mStringBuilder.append("    **error:" + error.toDescriptiveString() + "\n");
        mAllErrorsFound.add(error);
    }
}
