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
    private int lineNumber = 0;

    //Take in file.
    //1)Check for blank lines & comments, if found then remove, add remaining to line to be scanned
    //2)Scan line for label

    public StringBuilder parseFile(File f){
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
        return mStringBuilder;
    }

    /**
     * Checks the current line, if no blanks, removes comments, then adds it to the log file. After this, checks for label
     *
     * @return
     */
    private void checkCurrentLine(String currentLine){
        String onlyCodeString = null;
        String label = null;
        String instructionString;
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

        //[TODO] Add these labels to an arraylist and check to make sure they all match up
        if(checkLabel(label)){
            instructionString = scanner.next();
        } else{
            instructionString = label;
        }

        instruction = isValidInstruction(instructionString);

        while(scanner.hasNext()){
            operands += scanner.next();
        }
        //check for labels
        if(instruction == null){
            Error instructionError = new Error(ErrorType.INVALID_INSTRUCTION, instructionString);
            logError(instructionError);
            //Was not a valid instruction, so log it
        } else{
            if(instruction.areOperandsValid(operands)){
                //was a good line of code, time to move onto the next one
            } else{
                //[TODO] Log an error for invalid operands
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

    private boolean checkLabel(String potentialLabel){
        if(potentialLabel.contains(":")){
            //It is a label, else it is not
            mFoundLabels.add(potentialLabel);
            return true;
        } else{
            return false;
        }
    }

    private void addToLogFile(String toAdd){
        String trimmed = toAdd.trim();

        if(trimmed != null && !trimmed.isEmpty()) {
            mStringBuilder.append(""+ lineNumber + ".  " + trimmed + "\n");
        }
    }

    private void logError(Error error){
        mStringBuilder.append("**error:" + error.toString() + "\n");
    }

}
