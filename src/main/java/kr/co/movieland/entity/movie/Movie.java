package kr.co.movieland.entity.movie;

import kr.co.movieland.entity.attachment.Attachment;
import kr.co.movieland.entity.common.BaseEntity;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie extends BaseEntity {

  private String id;
  private String title;
  private String desc;
  private int duration;
  private Date release;
  private int ageLimit;
  private String directorName;
  private String leadActors;
  private String genre;

  List<Attachment> attachmentList;
  List<MovieReview> reviewList;

}
