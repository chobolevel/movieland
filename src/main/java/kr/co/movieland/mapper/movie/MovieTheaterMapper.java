package kr.co.movieland.mapper.movie;

import kr.co.movieland.entity.movie.MovieTheater;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieTheaterMapper {

  List<MovieTheater> findAll(MovieTheater movieTheater);

  MovieTheater findOne(MovieTheater movieTheater);

  void create(MovieTheater movieTheater);

  void modify(MovieTheater movieTheater);

  void delete(MovieTheater movieTheater);

}
