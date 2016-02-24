import java.util.ArrayList;

/**
 * Created by Ryan on 2/24/2016.
 */
public class LogInfo {
    private StringBuilder mLogInfo;
    private ArrayList<Error> mErrors;

    public LogInfo(StringBuilder stringBuilder, ArrayList<Error> errors){
        mLogInfo = stringBuilder;
        mErrors = errors;
    }

    public StringBuilder getLogInfo() {
        return mLogInfo;
    }

    public ArrayList<Error> getErrors() {
        return mErrors;
    }
}
