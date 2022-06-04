package com.developerblog.app.ws.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.developerblog.app.ws.uiModelResponse.ErrorMessage;

/*
*BEFORE:
 * @ControllerAdvice// This annotation is used to handle all exceptions in the
 * application.
 * public class AppExceptionsHandler extends ResponseEntityExceptionHandler {
 * 
 * @ExceptionHandler(value = { Exception.class })
 * public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest
 * request) {
 * return new ResponseEntity<>(ex, new HttpHeaders(),
 * HttpStatus.INTERNAL_SERVER_ERROR);
 * }
 * }
 * 
 */

 //UPDATED:
 @ControllerAdvice // This annotation is used to handle all exceptions in the application.
 public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(value = { Exception.class })
     public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

         String errorMessageDescription = ex.getLocalizedMessage();

         if (errorMessageDescription == null)
             errorMessageDescription = ex.toString();
         ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
         return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
     }

     // HANDLE SPECIFIC EXCEPTIONS: in this example we are handling the NullPointerException
     
/*
 * 
 * @ExceptionHandler(value = { NullPointerException.class })
 * public ResponseEntity<Object> handleNullPointerException(NullPointerException
 * ex, WebRequest request) {
 * 
 * String errorMessageDescription = ex.getLocalizedMessage();
 * 
 * if (errorMessageDescription == null)
 * errorMessageDescription = ex.toString();
 * ErrorMessage errorMessage = new ErrorMessage(new Date(),
 * errorMessageDescription);
 * return new ResponseEntity<>(errorMessage, new HttpHeaders(),
 * HttpStatus.INTERNAL_SERVER_ERROR);
 * }
 */


    //  HANDLE CUSTOM EXCEPTIONS: in this example we are handling the
    //  userServiceException

/*
 * @ExceptionHandler(value = { UserServiceException.class })
 * public ResponseEntity<Object>
 * handleUserServiceExceptionException(UserServiceException ex, WebRequest
 * request) {
 * 
 * String errorMessageDescription = ex.getLocalizedMessage();
 * 
 * if (errorMessageDescription == null)
 * errorMessageDescription = ex.toString();
 * ErrorMessage errorMessage = new ErrorMessage(new Date(),
 * errorMessageDescription);
 * return new ResponseEntity<>(errorMessage, new HttpHeaders(),
 * HttpStatus.INTERNAL_SERVER_ERROR);
 * }
 * 
 */

     //COMBINING THE HANDLE SPECIFIC EXCEPTIONS AND CUSTOM EXCEPTIONS: to one method

     @ExceptionHandler(value = { NullPointerException.class, UserServiceException.class })
     public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {

         String errorMessageDescription = ex.getLocalizedMessage();

         if (errorMessageDescription == null)
             errorMessageDescription = ex.toString();
         ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
         return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
     }
 }

 
