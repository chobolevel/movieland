package kr.co.movieland.entity.account;

import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountMovieTicket extends BaseEntity {

  private String accountId;
  private String movieTicketId;

  private List<String> movieTicketIdList;

}
