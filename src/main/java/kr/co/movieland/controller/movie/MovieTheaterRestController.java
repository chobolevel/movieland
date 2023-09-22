package kr.co.movieland.controller.movie;

import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.entity.movie.MovieTheater;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.movie.MovieTheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-theater")
@RequiredArgsConstructor
public class MovieTheaterRestController {

  private final MovieTheaterService movieTheaterService;

  @GetMapping("list")
  public ResponseEntity<?> getMovieTheaterList() throws ApiException {
    return new ResponseEntity<>(movieTheaterService.findAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getMovieTheater(@PathVariable String id) throws ApiException {
    return new ResponseEntity<>(movieTheaterService.findOne(MovieTheater.builder().id(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createMovieTheater(@RequestBody MovieTheater movieTheater) throws ApiException {
    movieTheaterService.create(movieTheater);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyMovieTheater(@PathVariable String id, @RequestBody MovieTheater movieTheater) throws ApiException {
    movieTheater.setId(id);
    movieTheaterService.modify(movieTheater);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteMovieTheater(@PathVariable String id) throws ApiException {
    movieTheaterService.delete(MovieTheater.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
