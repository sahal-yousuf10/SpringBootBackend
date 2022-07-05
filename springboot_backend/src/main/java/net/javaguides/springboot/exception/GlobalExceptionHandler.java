package net.javaguides.springboot.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error)->{
//
//           String fieldName= ((FieldError) error).getField();
//           String message = error.getDefaultMessage();
//           errors.put(fieldName, message);
        ex.getBindingResult().getFieldErrors().forEach(x -> {errors.put(x.getField(), x.getDefaultMessage());});
        //return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
        //return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
        return errors;
    }

    //Handle specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetails handleResourceNotFoundException
            (ResourceNotFoundException exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        //return errorDetails;
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
        return errorDetails;
    }

    @ExceptionHandler(EmployeeAlreadyExistException.class)
    public ResponseEntity<?> handleEmployeeAlreadyExistException
            (EmployeeAlreadyExistException exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.CONFLICT);
    }

    //Handle specific exception
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException
    (APIException exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    // handle global exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException
            (Exception exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
