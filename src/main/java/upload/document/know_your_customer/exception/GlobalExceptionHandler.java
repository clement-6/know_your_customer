package upload.document.know_your_customer.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import upload.document.know_your_customer.exception.models.ManagementError;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, @NotNull HttpHeaders headers, @NotNull HttpStatusCode status, @NotNull WebRequest request) {
        List<ManagementError> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> new ManagementError(fieldError.getField(), fieldError.getDefaultMessage())).toList();

        ManagementErrorMessage managementErrorMessage = new ManagementErrorMessage(
                HttpStatus.BAD_REQUEST, ExceptionsMessages.M_INVALID_DATA.getCode(), ex.getMessage(), errors);

        return handleExceptionInternal(ex,managementErrorMessage,new HttpHeaders(), managementErrorMessage.getHttpStatus(),request);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException exc, WebRequest webRequest) {
        ManagementErrorMessage hrErrorMessage = new ManagementErrorMessage(
                HttpStatus.NOT_FOUND,
                ExceptionsMessages.M_RESOURCE_NOT_FOUND.getCode(),
                exc.getMessage(),
                null
        );
        return handleExceptionInternal(exc,hrErrorMessage,new HttpHeaders(),HttpStatus.NOT_FOUND,webRequest);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFoundException(ResourceNotFoundException exc, WebRequest webRequest) {
        ManagementErrorMessage hrErrorMessage = new ManagementErrorMessage(
                HttpStatus.NOT_FOUND,
                exc.getCode(),
                exc.getMessage(),
                null
        );
        return handleExceptionInternal(exc, hrErrorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException exc, WebRequest webRequest) {
        ManagementErrorMessage hrErrorMessage = new ManagementErrorMessage(
                HttpStatus.BAD_REQUEST,
                ExceptionsMessages.M_INVALID_DATA.getCode(),
                exc.getMessage(),
                null
        );
        return handleExceptionInternal(exc, hrErrorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }
}
