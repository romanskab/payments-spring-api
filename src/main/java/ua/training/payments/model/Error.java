package ua.training.payments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.training.payments.model.enums.ErrorCode;
import ua.training.payments.model.enums.ErrorType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Error {

    private String message;
    private ErrorCode errorCode;
    private ErrorType errorType;
    private LocalDateTime dateTime;

}
