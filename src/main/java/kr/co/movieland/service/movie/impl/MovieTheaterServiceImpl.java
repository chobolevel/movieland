package kr.co.movieland.service.movie.impl;

import kr.co.movieland.entity.movie.MovieTheater;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.movie.MovieTheaterMapper;
import kr.co.movieland.service.movie.MovieTheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieTheaterServiceImpl implements MovieTheaterService {

  private final MovieTheaterMapper movieTheaterMapper;

  @Override
  public List<MovieTheater> findAll() {
    return movieTheaterMapper.findAll(MovieTheater.builder().build());
  }

  @Override
  public MovieTheater findOne(MovieTheater movieTheater) {
    return movieTheaterMapper.findOne(movieTheater);
  }

  @Override
  public void create(MovieTheater movieTheater) throws ApiException {
    if(movieTheater.getName() == null || movieTheater.getName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "name", "String");
    } else if(movieTheater.getCity() == null || movieTheater.getCity().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "city", "String");
    } else if(movieTheater.getAddress() == null || movieTheater.getAddress().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "address", "String");
    }
    movieTheater.setId(UUID.randomUUID().toString());
    movieTheaterMapper.create(movieTheater);
  }

  @Override
  public void modify(MovieTheater movieTheater) throws ApiException {
    if(movieTheater.getId() == null || movieTheater.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTheaterMapper.modify(movieTheater);
  }

  @Override
  public void delete(MovieTheater movieTheater) throws ApiException {
    if(movieTheater.getId() == null || movieTheater.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieTheaterMapper.delete(movieTheater);
  }
}
