package kr.co.movieland.service.movie;

import kr.co.movieland.entity.movie.Movie;
import kr.co.movieland.exception.ApiException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {

  List<Movie> findAll();

  Movie findOne(Movie movie);

  void create(Movie movie, List<MultipartFile> uploadFiles) throws ApiException, IOException;

  void modify(Movie movie, List<MultipartFile> uploadFiles) throws ApiException, IOException;

  void delete(Movie movie) throws ApiException;

}
