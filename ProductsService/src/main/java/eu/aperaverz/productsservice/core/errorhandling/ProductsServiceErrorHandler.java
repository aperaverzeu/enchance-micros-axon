package eu.aperaverz.productsservice.core.errorhandling;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ProductsServiceErrorHandler {
    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException exception,
                                                              WebRequest request) {
        return createResponse(exception);
    }

    @ExceptionHandler(value = {CommandExecutionException.class})
    public ResponseEntity<Object> handleCommandException(CommandExecutionException exception,
                                                         WebRequest request) {
        return createResponse(exception);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception exception,
                                                       WebRequest request) {
        return createResponse(exception);
    }

    private ResponseEntity<Object> createResponse(Exception exception) {
        return new ResponseEntity<>(
                new ErrorMessage(new Date(), exception.getMessage()),
                new HttpHeaders(),
                INTERNAL_SERVER_ERROR);
    }
}
