/**
 * Created by Ryan on 2/23/2016.
 */
public class Error {
    private ErrorType mErrorType;
    private String mAdditionalInfo;

    public Error(ErrorType errorType, String additionalInfo){
        mErrorType = errorType;
        mAdditionalInfo = additionalInfo;
    }

    public String toString(){
        return mErrorType.toString();
    }

    public ErrorType getmErrorType() {
        return mErrorType;
    }

    public String toDescriptiveString(){
        return mErrorType.toString() + " " + mAdditionalInfo;
    }
}
