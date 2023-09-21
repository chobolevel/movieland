package kr.co.movieland.controller.exception;

import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionRestController {

  @ExceptionHandler(ApiException.class)
  public ResponseEntity<?> apiException(ApiException exception) {
    return new ResponseEntity<>(BaseResponse.getExceptionInstance(exception), exception.getType().getStatus());
  }

}
