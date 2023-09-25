package kr.co.movieland.entity.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mail {

  private String to;
  private int authNumber;

}
