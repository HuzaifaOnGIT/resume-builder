package com.te.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.te.inventory.dto.ResponseMessage;

@RestControllerAdvice
public class InventoryExceptionHandler extends ResponseEntityExceptionHandler {


//    @Override
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//            HttpHeaders headers, HttpStatus status, WebRequest request) {
//         List<String> list = ex.getBindingResult().getAllErrors().stream().map(t -> t.getDefaultMessage()).toList();
//       return new ResponseEntity<Object>(new ValidationResponse(true, list, null), HttpStatus.BAD_REQUEST);
////        return super.handleMethodArgumentNotValid(ex, headers, status, request);
//    }

    @ExceptionHandler(InventoryException.class)
    public ResponseEntity<ResponseMessage> exceptionhandler(InventoryException exception) {
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage> exceptionhandler(Exception exception) {
        return new ResponseEntity<ResponseMessage>(new ResponseMessage(true, exception.getMessage(), null),
                HttpStatus.BAD_REQUEST);
    }
   
  

}