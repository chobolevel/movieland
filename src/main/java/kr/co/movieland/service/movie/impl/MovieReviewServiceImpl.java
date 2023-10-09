package kr.co.movieland.service.movie.impl;

import kr.co.movieland.entity.movie.MovieReview;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.movie.MovieReviewMapper;
import kr.co.movieland.service.movie.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieReviewServiceImpl implements MovieReviewService {

  private final MovieReviewMapper movieReviewMapper;

  @Override
  public List<MovieReview> findAll(MovieReview movieReview) {
    return movieReviewMapper.findAll(movieReview);
  }

  @Override
  public void create(MovieReview movieReview) throws ApiException {
    if(movieReview.getMovieId() == null || movieReview.getMovieId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "movie_id", "String");
    } else if(movieReview.getAccountId() == null || movieReview.getAccountId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "account_id", "String");
    }
    MovieReview findOne = movieReviewMapper.findOne(MovieReview
        .builder()
        .movieId(movieReview.getMovieId())
        .accountId(movieReview.getAccountId())
        .build());
    if(findOne != null) {
      throw new ApiException(ApiExceptionType.REVIEW_ALREADY_WRITTEN);
    }
    movieReview.setId(UUID.randomUUID().toString());
    movieReviewMapper.create(movieReview);
  }

  @Override
  public void delete(MovieReview movieReview) throws ApiException {
    if(movieReview.getMovieId() == null || movieReview.getMovieId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "movie_id", "String");
    }
    movieReviewMapper.delete(movieReview);
  }
}
