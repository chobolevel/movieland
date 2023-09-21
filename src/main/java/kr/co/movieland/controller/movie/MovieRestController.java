package kr.co.movieland.controller.movie;

import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.entity.movie.Movie;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieRestController {

  private final MovieService movieService;

  @GetMapping("list")
  public ResponseEntity<?> getMovieList() {
    return new ResponseEntity<>(movieService.findAll(), HttpStatus.OK);
  }

  @GetMapping("{id}")
  public ResponseEntity<?> getMovie(@PathVariable String id) {
    return new ResponseEntity<>(movieService.findOne(Movie.builder().id(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createMovie(@RequestBody Movie movie, @RequestPart(required = false) List<MultipartFile> uploadFiles) throws IOException, ApiException {
    movieService.create(movie, uploadFiles);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyMovie(@PathVariable String id,
                                       @RequestBody Movie movie,
                                       @RequestPart(required = false) List<MultipartFile> uploadFiles) throws IOException, ApiException {
    movie.setId(id);
    movieService.modify(movie, uploadFiles);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteMovie(@PathVariable String id) throws ApiException {
    movieService.delete(Movie.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
