package kr.co.movieland.enums.Account;

import kr.co.movieland.enums.BaseEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AccountRoleType implements BaseEnum {
  ROLE_USER("서비스 사용자"),
  ROLE_ADMIN("서비스 관리자")
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
