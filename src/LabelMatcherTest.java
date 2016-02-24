import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Ryan on 2/24/2016.
 */
public class LabelMatcherTest {
    private ArrayList<String> mLabelsBranchedTo = new ArrayList<>();
    private ArrayList<String> mPossibleLabels = new ArrayList<>();
    private ArrayList<Error> mLabelErrors = new ArrayList<>();

    @Test
    public void testLabelsAreFine() throws Exception {
        LabelMatcher.addLabelThatIsAvailable("LOOP");
        LabelMatcher.addLabelThatIsAvailable("LOOP");
        LabelMatcher.addLabelThatIsAvailable("LOOP");

        LabelMatcher.addLabelThatWasBranchedTo("LOOP");

        mLabelErrors = LabelMatcher.getLabelErrors();
        int sizeE = mLabelErrors.size();
        int sizeA = 0;

        assertEquals(sizeE, sizeA);
    }

    @Test
    public void testBranchedToUnknownLabel() throws Exception {
        LabelMatcher.addLabelThatIsAvailable("LOOP");
        LabelMatcher.addLabelThatIsAvailable("LOOP");
        LabelMatcher.addLabelThatIsAvailable("LOOP");

        LabelMatcher.addLabelThatWasBranchedTo("BEGIN");

        mLabelErrors = LabelMatcher.getLabelErrors();
        int sizeA = mLabelErrors.size();
        int sizeE = 1;

        assertEquals(sizeE, sizeA);
    }

    @Test
    public void testLabelWasUnused() throws Exception {
        LabelMatcher.addLabelThatIsAvailable("END");
        LabelMatcher.addLabelThatIsAvailable("LOOP");
        LabelMatcher.addLabelThatIsAvailable("LOOP");

        LabelMatcher.addLabelThatWasBranchedTo("LOOP");

        mLabelErrors = LabelMatcher.getLabelErrors();
        int sizeE = mLabelErrors.size();
        int sizeA = 1;

        assertEquals(sizeE, sizeA);
    }
}