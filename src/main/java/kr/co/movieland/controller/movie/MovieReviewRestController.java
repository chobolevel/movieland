package kr.co.movieland.controller.movie;

import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.entity.movie.MovieReview;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.movie.MovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie-review")
public class MovieReviewRestController {

  private final MovieReviewService movieReviewService;

  @GetMapping("/{id}/list")
  public ResponseEntity<?> getList(@PathVariable String id) {
    return new ResponseEntity<>(movieReviewService.findAll(MovieReview.builder().id(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createMovieReview(@RequestBody MovieReview movieReview) throws ApiException {
    movieReviewService.create(movieReview);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

}
