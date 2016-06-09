import java.util.ArrayList;

/**
 * Created by Ryan on 2/21/2016.
 * Contains the log information needed to output information to a file
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

    /**
     * All the errors the Syntax Checker found
     * @return
     */
    public ArrayList<Error> getErrors() {
        return mErrors;
    }
}
