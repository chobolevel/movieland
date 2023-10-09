package kr.co.movieland.entity.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity {

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String createDate;
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private String updateDate;
  @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
  private int start;
  @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
  private int count;

}
