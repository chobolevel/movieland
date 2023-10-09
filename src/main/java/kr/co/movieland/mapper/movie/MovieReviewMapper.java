package kr.co.movieland.mapper.movie;

import kr.co.movieland.entity.movie.MovieReview;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieReviewMapper {

  List<MovieReview> findAll(MovieReview movieReview);

  MovieReview findOne(MovieReview movieReview);

  void create(MovieReview movieReview);

  void delete(MovieReview movieReview);

}
