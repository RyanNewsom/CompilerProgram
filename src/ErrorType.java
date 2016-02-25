/**
 * Created by Ryan on 2/21/2016.
 * The different types of errors that the Syntac Checker can find
 */
public enum ErrorType {
    ILL_FORMED_OPERAND, ILL_FORMED_LABEL, INVALID_INSTRUCTION, TOO_MANY_OPERANDS, TOO_FEW_OPERANDS, WRONG_OPERAND_TYPE,
    LABEL_NOT_USED, LABEL_DOESNT_EXIST
}
