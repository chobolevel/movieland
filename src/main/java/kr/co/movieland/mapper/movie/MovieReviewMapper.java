package kr.co.movieland.mapper.movie;

import kr.co.movieland.entity.movie.MovieReview;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieReviewMapper {

  MovieReview findOne(MovieReview movieReview);

  void create(MovieReview movieReview);

  void delete(MovieReview movieReview);

}
