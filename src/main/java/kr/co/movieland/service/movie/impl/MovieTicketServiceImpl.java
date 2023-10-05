package kr.co.movieland.service.movie.impl;

import kr.co.movieland.entity.movie.MovieTicket;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.movie.MovieTicketMapper;
import kr.co.movieland.service.movie.MovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieTicketServiceImpl implements MovieTicketService {

  private final MovieTicketMapper movieTicketMapper;

  @Override
  public List<MovieTicket> findAll(MovieTicket movieTicket) {
    return movieTicketMapper.findAll(movieTicket);
  }

  @Override
  public MovieTicket findOne(MovieTicket movieTicket) {
    return movieTicketMapper.findOne(movieTicket);
  }

  @Override
  public void create(MovieTicket movieTicket) throws ApiException {
    if (movieTicket.getMovieId() == null || movieTicket.getMovieId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "movieId", "String");
    } else if (movieTicket.getTheaterId() == null || movieTicket.getTheaterId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "theaterId", "String");
    } else if (movieTicket.getRowStart() == null || movieTicket.getRowStart().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "rowStart", "String");
    } else if (movieTicket.getRowEnd() == null || movieTicket.getRowEnd().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "rowEnd", "String");
    } else if (movieTicket.getColumnStart() == null || movieTicket.getColumnStart().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "columnStart", "String");
    } else if (movieTicket.getColumnEnd() == null || movieTicket.getColumnEnd().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "columnEnd", "String");
    } else if (movieTicket.getScreenDatetime() == null) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "screenDatetime", "Datetime");
    }
    List<MovieTicket> movieTicketList = new ArrayList<>();
    char columnStart = movieTicket.getColumnStart().charAt(0);
    char columnEnd = movieTicket.getColumnEnd().charAt(0);
    int rowStart = Integer.parseInt(movieTicket.getRowStart());
    int rowEnd = Integer.parseInt(movieTicket.getRowEnd());
    for (char c = columnStart; c <= columnEnd; c++) {
      for (int i = rowStart; i <= rowEnd; i++) {
        movieTicketList.add(MovieTicket
            .builder()
            .id(UUID.randomUUID().toString())
            .movieId(movieTicket.getMovieId())
            .theaterId(movieTicket.getTheaterId())
            .seatNumber(String.format("%s-%d", c, i))
            .screenDatetime(movieTicket.getScreenDatetime())
            .build());
      }
    }
    movieTicketMapper.create(movieTicketList);
  }

  @Override
  public void modify(MovieTicket movieTicket) throws ApiException {
    if (movieTicket.getId() == null || movieTicket.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTicketMapper.modify(movieTicket);
  }

  @Override
  public void delete(MovieTicket movieTicket) throws ApiException {
    if (movieTicket.getId() == null || movieTicket.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTicketMapper.delete(movieTicket);
  }
}
