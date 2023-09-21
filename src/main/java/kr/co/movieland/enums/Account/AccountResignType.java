package kr.co.movieland.enums.Account;

import kr.co.movieland.enums.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountResignType implements BaseEnum {
  RESIGN("y"),
  NON_RESIGN("n")
  ;

  private final String name;

  @Override
  public String getCode() {
    return name();
  }

  @Override
  public String getName() {
    return name;
  }
}
