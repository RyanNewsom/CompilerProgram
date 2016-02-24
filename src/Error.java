/**
 * Created by Ryan on 2/23/2016.
 */
public class Error {
    private ErrorType mErrorType;

    public Error(ErrorType errorType, String additionalInfo){
        mErrorType = errorType;
    }

    public String toString(){
        return mErrorType.toString();
    }

    public ErrorType getmErrorType() {
        return mErrorType;
    }
}
