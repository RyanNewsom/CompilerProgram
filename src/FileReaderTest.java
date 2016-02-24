import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 1/31/2016.
 */
public class FileReaderTest {
    //Comments
    File test1_a = new File("testComments1.txt");
    File test1_e = new File("testComments1-expected.txt");

    //Labels
    File test2_a = new File("testLabels1.txt");
    File test2_e = new File("testLabels1-expected.txt");

    FileReader fileReader = new FileReader();
    StringBuilder mActual;
    StringBuilder mExpected;

    @Before
    public void setUp() throws Exception {
        mExpected = new StringBuilder();
    }

    @After
    public void tearDown() throws Exception {

    }

    private void setUpFiles(File expectedFile){
        InputStream inputStream = null;
        BufferedReader br = null;
        String currentLine;
        try {
            inputStream = new FileInputStream(expectedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        br = new BufferedReader(new InputStreamReader(inputStream));
        try {
            while ((currentLine = br.readLine()) != null) {
                mExpected.append(currentLine + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommentsParse(){
        String actual;
        String expected;

        setUpFiles(test1_e);
        mActual = fileReader.parseFile(test1_a).getLogInfo();
        expected = mExpected.toString();
        actual = mActual.toString();

        assertEquals(actual, expected);
    }


}