package kr.co.movieland.entity.movie;

import kr.co.movieland.entity.account.Account;
import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieReview extends BaseEntity {

  private String id;
  private String movieId;
  private String accountId;
  private String content;
  private int scope;

  private Account account;

}
