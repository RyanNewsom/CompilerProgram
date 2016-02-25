/**
 * Created by Ryan on 2/21/2016.
 */
public class Error {
    private ErrorType mErrorType;
    private String mAdditionalInfo;

    /**
     * Constructor - An error in a particular instruction, operand, label, etc.
     * @param errorType - Errortype Enum
     * @param additionalInfo - additional descriptive text to be passed
     */
    public Error(ErrorType errorType, String additionalInfo){
        mErrorType = errorType;
        mAdditionalInfo = additionalInfo;
    }

    private Error(){}

    public ErrorType getErrorType() {
        return mErrorType;
    }

    public String toString(){
        return mErrorType.toString();
    }

    /**
     * prints a string with the error type and some additional info
     * @return
     */
    public String toAdditionalInfoString(){
        return mErrorType.toString() + " " + mAdditionalInfo;
    }
}
