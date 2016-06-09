//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.Assert.*;
//
///**DISABLED UNTIL I CAN FIND A WAY TO CLEAR A STATIC CLASS'S VARIABLES DURING TEST
// * Created by Ryan on 2/24/2016.
// */
//public class LabelMatcherTest {
//    private ArrayList<Error> mLabelErrors = new ArrayList<>();
//
//    @After
//    public void tearDown() throws Exception {
//        mLabelErrors.clear();
//    }
//
//    @Test
//    public void testNoMatchingMultipleErrors() throws Exception {
//        LabelMatcher.addLabelThatIsAvailable("END");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//
//        LabelMatcher.addLabelThatWasBranchedTo("START");
//
//        mLabelErrors = LabelMatcher.getLabelErrors();
//        int sizeA = mLabelErrors.size();
//        int sizeE = 2;
//
//        assertEquals(sizeE, sizeA);
//    }
//
//    @Test
//    public void testLabelsAreFine() throws Exception {
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//
//        LabelMatcher.addLabelThatWasBranchedTo("loop");
//
//        mLabelErrors = LabelMatcher.getLabelErrors();
//        int sizeA = mLabelErrors.size();
//        int sizeE = 0;
//
//        assertEquals(sizeE, sizeA);
//    }
//
//    @Test
//    public void testUnusedLabel() throws Exception {
//        LabelMatcher.addLabelThatIsAvailable("END");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//
//        LabelMatcher.addLabelThatWasBranchedTo("LOOP");
//
//        mLabelErrors = LabelMatcher.getLabelErrors();
//        int sizeA = mLabelErrors.size();
//        int sizeE = 1;
//
//        assertEquals(sizeE, sizeA);
//    }
//
//    @Test
//    public void testExtraLabelAvail() throws Exception {
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//
//        mLabelErrors = LabelMatcher.getLabelErrors();
//        int sizeA = mLabelErrors.size();
//        int sizeE = 1;
//
//        assertEquals(sizeE, sizeA);
//    }
//
//    @Test
//    public void testNoErrors() throws Exception {
//        mLabelErrors.clear();
//
//        LabelMatcher.addLabelThatIsAvailable("START");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//        LabelMatcher.addLabelThatIsAvailable("LOOP");
//
//        LabelMatcher.addLabelThatWasBranchedTo("START");
//        LabelMatcher.addLabelThatWasBranchedTo("LOOP");
//
//
//        mLabelErrors = LabelMatcher.getLabelErrors();
//        int sizeA = mLabelErrors.size();
//        int sizeE = 0;
//
//        assertEquals(sizeE, sizeA);
//    }
//
//}