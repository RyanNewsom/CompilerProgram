import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Ryan on 2/6/2016.
 */
public class TesterProgram {
    private static File[] mFiles = new File[2];
    public static void main(String [] args){
        getFilesFromUser();
        boolean areSame = compareFiles();
        if(areSame){
            System.out.println("The files are the same, test passed");
        } else{
            System.out.println("The files are not the same, test failed");
        }
    }

    private static void getFilesFromUser(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the first files name, containing the actual output");
        String fileActual = in.next();
        System.out.println("Enter the second files name, containing the expected output");
        String fileExpected = in.next();
        File fileA = new File(fileActual);
        File fileB = new File(fileExpected);
        mFiles[0] = fileA;
        mFiles[1] = fileB;
    }

    private static boolean compareFiles(){
        boolean areSame = false;
        Scanner s1;
        Scanner s2;
        try {
            s1 = new Scanner(mFiles[0]);
            s2 = new Scanner(mFiles[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        if(s1.hasNext() == false && s2.hasNext() == false){
            //empty file, they are the same
            return true;
        }

        while(s1.hasNext()){
            String s1String;
            String s2String;

            if(s2.hasNext()){
                s1String = s1.next();
                s2String = s2.next();

                if(s1String.equals(s2String)){
                    areSame = true;
                } else{
                    areSame = false;
                    break;
                }
            }
        }

        return areSame;
    }


}
