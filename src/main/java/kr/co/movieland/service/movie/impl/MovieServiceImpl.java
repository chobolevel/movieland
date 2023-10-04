package kr.co.movieland.service.movie.impl;

import kr.co.movieland.entity.attachment.Attachment;
import kr.co.movieland.entity.movie.Movie;
import kr.co.movieland.enums.common.ApiExceptionType;
import kr.co.movieland.exception.ApiException;
import kr.co.movieland.mapper.attachment.AttachmentMapper;
import kr.co.movieland.mapper.movie.MovieMapper;
import kr.co.movieland.service.movie.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieServiceImpl implements MovieService {

  private final MovieMapper movieMapper;
  private final AttachmentMapper attachmentMapper;
  @Value("${spring.servlet.multipart.location}")
  private String basePath;

  @Override
  public List<Movie> findAll() {
    return movieMapper.findAll(Movie.builder().build());
  }

  @Override
  public Movie findOne(Movie movie) {
    return movieMapper.findOne(movie);
  }

  @Override
  public void create(Movie movie, List<MultipartFile> uploadFiles) throws ApiException, IOException {
    if(movie.getTitle() == null || movie.getTitle().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "title", "String");
    } else if(movie.getDesc() == null || movie.getDesc().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "desc", "String");
    } else if(movie.getDuration() == 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "duration", "int");
    } else if(movie.getRelease() == null) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "release", "Date");
    } else if(movie.getAgeLimit() == 0) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "age_limit", "int");
    } else if(movie.getDirectorName() == null || movie.getDirectorName().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "director_name", "String");
    }
    String movieId = UUID.randomUUID().toString();
    movie.setId(movieId);
    movieMapper.create(movie);
    if(uploadFiles != null) {
      List<Attachment> attachmentList = uploadFiles
          .stream().map((file) -> new Attachment(null, movieId, file.getOriginalFilename()))
          .toList();
      File folder = new File(String.format("%s\\%s", basePath, movieId));
      folder.mkdir();
      for (MultipartFile file : uploadFiles) {
        file.transferTo(new File(String.format("%s\\%s", movieId, file.getOriginalFilename())));
      }
      attachmentMapper.creates(attachmentList);
    }
  }

  @Override
  public void modify(Movie movie, List<MultipartFile> uploadFiles) throws ApiException, IOException {
    if(movie.getId() == null || movie.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }

    if(uploadFiles != null) {
      File folder = new File(String.format("%s\\%s", basePath, movie.getId()));
      List<Attachment> attachmentList = uploadFiles
          .stream().map((file) -> new Attachment(null, movie.getId(), file.getOriginalFilename()))
          .toList();
      if(folder.exists()) {
        // 폴더 존재하는 경우
        attachmentMapper.delete(Attachment.builder().movieId(movie.getId()).build());
        if(folder.listFiles() != null) {
          for(File file : folder.listFiles()) {
            file.delete();
          }
        }
        folder.delete();
        folder.mkdir();
      } else {
        // 폴더 존재하지 않는 경우
        folder.mkdir();
      }
      for(MultipartFile file : uploadFiles) {
        file.transferTo(new File(String.format("%s\\%s", movie.getId(), file.getOriginalFilename())));
      }
      attachmentMapper.creates(attachmentList);
    }

    movieMapper.modify(movie);
  }

  @Override
  public void delete(Movie movie) throws ApiException {
    if(movie.getId() == null || movie.getId().isEmpty()) {
      throw new ApiException(ApiExceptionType.MISSING_PARAMETER, "id", "String");
    }
    movieMapper.delete(movie);
  }
}
