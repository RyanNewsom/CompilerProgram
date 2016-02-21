import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ryan on 1/24/2016.
 * The Driver for the MAL Syntax Checker program. Here file's will be opened, the files scanned, with the log files being
 * outputted after error checking.
 */
public class Driver {
    private static List<StringBuilder> mNewFiles = new ArrayList<>();
    private static StringBuilder logString;

    //Loop through the possible files
    //Open the current file
    //Look for any items to be stripped
    //Remove the items and output the new text to an output file
    public static void main(String [] args){
        File file = loadFile();
        readFile(file);
        outputFiles();
    }


    /**
     * Reads all the files and produces a string builder which will then be written to a new file
     */
    private static void readFile(File file){
        FileReader fileReader = new FileReader();
        logString = fileReader.parseFile(file);
    }

    /**
     * Outputs the new files with blank lines & comments removed
     */
    private static void outputFiles() {
        BufferedWriter output = null;

        try {
            output = new BufferedWriter(new FileWriter("Log.txt"));
            output.write(logString.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (output != null) try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File loadFile(){
        String fileName;
        File theFile;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the MAL files name, so that it may be checked for compilation errors.");
        fileName = in.next();
        theFile = new File(fileName);

        return theFile;
    }

}
