package kr.co.movieland.service.movie;

import kr.co.movieland.entity.movie.MovieReview;
import kr.co.movieland.exception.ApiException;

import java.util.List;

public interface MovieReviewService {

  List<MovieReview> findAll(MovieReview movieReview);

  void create(MovieReview movieReview) throws ApiException;

  void delete(MovieReview movieReview) throws ApiException;

}
