package kr.co.movieland.enums.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ApiExceptionType {

  MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "%s[Type: %s]이/가 누락되었습니다."),
  REVIEW_ALREADY_WRITTEN(HttpStatus.BAD_REQUEST, "이미 리뷰를 작성하였습니다."),
  ACCOUNT_NOT_EXIST(HttpStatus.BAD_REQUEST, "회원 정보가 존재하지 않습니다."),
  AUTH_NUMBER_NOT_MATCH(HttpStatus.BAD_REQUEST, "인증 번호가 일치하지 않습니다."),
  USERNAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다.")
  ;

  private final HttpStatus status;
  private final String message;

}

