package kr.co.movieland.entity.common;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

  private Date createDate;
  private Date updateDate;

}
