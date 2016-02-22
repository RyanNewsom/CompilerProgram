import java.io.*;

/**
 * Created by Ryan on 1/24/2016.
 * Parses each file
 */
public class FileReader {
    private static final String TAG = "FileReader";
    private StringBuilder mStringBuilder = new StringBuilder();

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
        if(!checkForBlankLines(currentLine)) {
            onlyCodeString = removeComments(currentLine);
            addToLogFile(onlyCodeString);
        }

        checkLabel();

        //check for labels


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

    private void checkLabel(){

    }

    private void checkOperands(){

    }

    private void addToLogFile(String toAdd){
        String trimmed = toAdd.trim();

        if(trimmed != null && !trimmed.isEmpty()) {
            mStringBuilder.append(trimmed + "\n");
        }
    }
}
