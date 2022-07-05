//package net.javaguides.springboot.validation;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.HashMap;
//import java.util.Map;
//
////@RestControllerAdvice
//public class ValidationHandler extends ResponseEntityExceptionHandler {
//
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//
//        Map<String, String> errors = new HashMap<>();
////        ex.getBindingResult().getAllErrors().forEach((error)->{
////
////           String fieldName= ((FieldError) error).getField();
////           String message = error.getDefaultMessage();
////           errors.put(fieldName, message);
//        ex.getBindingResult().getFieldErrors().forEach(x -> {errors.put(x.getField(), x.getDefaultMessage());});
//        //return new ResponseEntity<Object>(errors, HttpStatus.BAD_REQUEST);
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
//    }
//}
