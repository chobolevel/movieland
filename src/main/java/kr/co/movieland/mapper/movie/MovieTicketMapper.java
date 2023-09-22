package kr.co.movieland.mapper.movie;

import kr.co.movieland.entity.movie.MovieTicket;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieTicketMapper {

  List<MovieTicket> findAll(MovieTicket movieTicket);

  MovieTicket findOne(MovieTicket movieTicket);

  void create(MovieTicket movieTicket);

  void modify(MovieTicket movieTicket);

  void delete(MovieTicket movieTicket);

}
