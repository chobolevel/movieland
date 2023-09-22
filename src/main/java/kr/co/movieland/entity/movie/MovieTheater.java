package kr.co.movieland.entity.movie;

import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieTheater extends BaseEntity {

  private String id;
  private String name;
  private String city;
  private String address;
}
