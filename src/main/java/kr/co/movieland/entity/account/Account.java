package kr.co.movieland.entity.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.co.movieland.entity.common.BaseEntity;
import kr.co.movieland.enums.Account.AccountRoleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseEntity {

  private String id;
  private String username;
  @JsonProperty( access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private String name;
  private String email;
  private AccountRoleType role;
  private String resignYn;

  private String authNumber;

}
