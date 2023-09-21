package kr.co.movieland.entity.common;

import kr.co.movieland.exception.ApiException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@Builder
public class BaseResponse {

  private String timestamp;
  private int statusCode;
  private String statusText;
  private String message;

  public static BaseResponse getInstance(HttpStatus httpStatus) {
    return BaseResponse
        .builder()
        .timestamp(new Date().toString())
        .statusCode(httpStatus.value())
        .statusText(httpStatus.getReasonPhrase())
        .build();
  }

  public static BaseResponse getExceptionInstance(ApiException exception) {
    return BaseResponse
        .builder()
        .timestamp(new Date().toString())
        .statusCode(exception.getType().getStatus().value())
        .statusText(exception.getType().getStatus().getReasonPhrase())
        .message(exception.getMessage())
        .build();
  }

}
