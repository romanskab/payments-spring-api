package ua.training.payments.exception;

import ua.training.payments.model.enums.ErrorCode;
import ua.training.payments.model.enums.ErrorType;

public class UserNotFoundException extends AbstractException{
    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public ErrorCode getErrorCode() {
        return ErrorCode.APPLICATION_ERROR_CODE;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.PROCESSING_ERROR_TYPE;
    }
}
