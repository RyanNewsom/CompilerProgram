import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 1/31/2016.
 */
public class FileReaderTest {
    File test1_a = new File("testComments1.txt");
    File test1_e = new File("testComments1-expected.txt");

    FileReader fileReader = new FileReader();
    StringBuilder mActual;
    StringBuilder mExpected;

    @Before
    public void setUp() throws Exception {
        mExpected = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader br = null;
        String currentLine;

        try {
            inputStream = new FileInputStream(test1_e);
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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCommentsParse(){
        String actual;
        String expected;

        mActual = fileReader.parseFile(test1_a);
        expected = mExpected.toString();
        actual = mActual.toString();

        assertEquals(actual, expected);
    }

}