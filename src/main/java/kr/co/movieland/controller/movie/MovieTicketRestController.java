package kr.co.movieland.controller.movie;

import kr.co.movieland.entity.common.BaseResponse;
import kr.co.movieland.entity.movie.MovieTicket;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.service.movie.MovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie-ticket")
@RequiredArgsConstructor
public class MovieTicketRestController {

  private final MovieTicketService movieTicketService;

  @GetMapping("{id}")
  public ResponseEntity<?> getMovieTicketList(@PathVariable String id) {
    return new ResponseEntity<>(movieTicketService.findAll(MovieTicket.builder().movieId(id).build()), HttpStatus.OK);
  }

  @PostMapping("")
  public ResponseEntity<?> createMovieTicket(@RequestBody MovieTicket movieTicket) throws ApiException {
    movieTicketService.create(movieTicket);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.CREATED), HttpStatus.CREATED);
  }

  @PutMapping("{id}")
  public ResponseEntity<?> modifyMovieTicket(@PathVariable String id, @RequestBody MovieTicket movieTicket) throws ApiException {
    movieTicket.setId(id);
    movieTicketService.modify(movieTicket);
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> deleteMovieTicket(@PathVariable String id) throws ApiException {
    movieTicketService.delete(MovieTicket.builder().id(id).build());
    return new ResponseEntity<>(BaseResponse.getInstance(HttpStatus.OK), HttpStatus.OK);
  }

}
