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
  private Date screenDatetime;

}
