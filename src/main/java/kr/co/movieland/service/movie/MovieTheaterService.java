package kr.co.movieland.service.movie;

import kr.co.movieland.entity.movie.MovieTheater;
import kr.co.movieland.exception.ApiException;

import java.util.List;

public interface MovieTheaterService {

  List<MovieTheater> findAll();

  MovieTheater findOne(MovieTheater movieTheater);

  void create(MovieTheater movieTheater) throws ApiException;

  void modify(MovieTheater movieTheater) throws ApiException;

  void delete(MovieTheater movieTheater) throws ApiException;

}
