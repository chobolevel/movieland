package kr.co.movieland.entity.movie;

import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieTicket extends BaseEntity {

  private String id;
  private String movieId;
  private String theaterId;
  private String seatNumber;
  // seat-number 생성 위한 property
  private String rowStart;
  private String rowEnd;
  private String columnStart;
  private String columnEnd;

  private Date screenDatetime;

}
