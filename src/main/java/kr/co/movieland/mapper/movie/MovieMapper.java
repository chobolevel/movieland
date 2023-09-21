package kr.co.movieland.mapper.movie;

import kr.co.movieland.entity.movie.Movie;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {

  List<Movie> findAll(Movie movie);

  Movie findOne(Movie movie);

  void create(Movie movie);

  void modify(Movie movie);

  void delete(Movie movie);

}
