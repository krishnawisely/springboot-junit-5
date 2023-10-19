package com.krishna.junit5testcase.exception;


public class BusinessException extends Exception {
   public BusinessException(String message){
    super(message);
   }

   public BusinessException(String message, Throwable throwable){
      super(message, throwable);
     }
}
