package kr.co.movieland.mapper.attachment;

import kr.co.movieland.entity.attachment.Attachment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachmentMapper {

  void creates(List<Attachment> attachmentList);

  void delete(Attachment attachment);

}
