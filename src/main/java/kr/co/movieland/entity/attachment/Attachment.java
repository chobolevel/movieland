package kr.co.movieland.entity.attachment;

import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attachment extends BaseEntity {

  private String accountId;
  private String movieId;
  private String name;

}
