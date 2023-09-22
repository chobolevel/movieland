package kr.co.movieland.service.movie;

import kr.co.movieland.entity.movie.MovieTicket;
import kr.co.movieland.exception.ApiException;

import java.util.List;

public interface MovieTicketService {

  List<MovieTicket> findAll(MovieTicket movieTicket);

  MovieTicket findOne(MovieTicket movieTicket);

  void create(MovieTicket movieTicket) throws ApiException;

  void modify(MovieTicket movieTicket) throws ApiException;

  void delete(MovieTicket movieTicket) throws ApiException;

}
