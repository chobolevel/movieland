package kr.co.movieland.service.movie.impl;

import kr.co.movieland.entity.movie.MovieTicket;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.movie.MovieTicketMapper;
import kr.co.movieland.service.movie.MovieTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    if(movieTicket.getTheaterId() == null || movieTicket.getTheaterId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "theaterId", "String");
    } else if(movieTicket.getSeatNumber() == null || movieTicket.getSeatNumber().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "seatNumber", "String");
    } else if(movieTicket.getScreenDatetime() == null) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "screenDatetime", "Date");
    }
    movieTicket.setId(UUID.randomUUID().toString());
    movieTicketMapper.create(movieTicket);
  }

  @Override
  public void modify(MovieTicket movieTicket) throws ApiException {
    if(movieTicket.getId() == null || movieTicket.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTicketMapper.modify(movieTicket);
  }

  @Override
  public void delete(MovieTicket movieTicket) throws ApiException {
    if(movieTicket.getId() == null || movieTicket.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTicketMapper.delete(movieTicket);
  }
}
