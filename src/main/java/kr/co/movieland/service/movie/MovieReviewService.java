package kr.co.movieland.service.movie;

import kr.co.movieland.entity.movie.MovieReview;
import kr.co.movieland.exception.ApiException;

public interface MovieReviewService {

  void create(MovieReview movieReview) throws ApiException;

  void delete(MovieReview movieReview) throws ApiException;

}
