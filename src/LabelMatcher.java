import java.util.ArrayList;

/**
 * Created by Ryan on 2/24/2016.
 */
public class LabelMatcher {
    private static ArrayList<String> mLabelsBranchedTo = new ArrayList<>();
    private static ArrayList<String> mPossibleLabels = new ArrayList<>();
    private static ArrayList<Error> mLabelErrors = new ArrayList<>();

    public static void addLabelThatWasBranchedTo(String label){
        boolean alreadyContains = false;
        for(int i = 0; i < mLabelsBranchedTo.size(); i++){
            String existingLabel = mLabelsBranchedTo.get(i);
            if(existingLabel.equalsIgnoreCase(label)){
                alreadyContains = true;
            }
        }
        if(!alreadyContains) {
            mLabelsBranchedTo.add(label);
        }
    }

    public static void addLabelThatIsAvailable(String label){
        boolean alreadyContains = false;
        for(int i = 0; i < mPossibleLabels.size(); i++){
            String existingLabel = mPossibleLabels.get(i);
            if(existingLabel.equalsIgnoreCase(label)){
                alreadyContains = true;
            }
        }
        if(!alreadyContains) {
            mPossibleLabels.add(label);
        }
    }

    public static ArrayList<Error> getLabelErrors(){
        boolean foundMatch = false;
        for(int i = 0; i < mPossibleLabels.size(); i++){
            String possible = mPossibleLabels.get(i);
            for(int j = 0; j < mLabelsBranchedTo.size(); j++){
                //compare each possible label with each label that was branched to
                String branched = mLabelsBranchedTo.get(j);
                if(possible.equals(branched)){
                    foundMatch = true;
                    break;
                }
            }
            if(!foundMatch){
                mLabelErrors.add(new Error(ErrorType.LABEL_NOT_USED, mPossibleLabels.get(i)));
            }
            foundMatch = false;
        }

        for(int i = 0; i < mLabelsBranchedTo.size(); i++){
            String branched = mLabelsBranchedTo.get(i);
            for(int j = 0; j < mPossibleLabels.size(); j++){
                //compare each possible label with each label that was branched to
                String possible = mPossibleLabels.get(j);
                if(branched.equals(possible)){
                    foundMatch = true;
                    break;
                }
            }
            if(!foundMatch){
                mLabelErrors.add(new Error(ErrorType.LABEL_DOESNT_EXIST, mLabelsBranchedTo.get(i)));
            }
            foundMatch = false;
        }

        return mLabelErrors;
    }
}
