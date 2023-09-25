package kr.co.movieland.util;

import kr.co.movieland.entity.common.Mail;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

public class CommonUtil {

  public static int generateAuthNumber() {
    Random random = new Random();
    return random.nextInt(100000, 999999);
  }

  public static void sendAuthNumber(JavaMailSender mailSender, Mail mail) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
    mimeMessageHelper.setTo(mail.getTo());
    mimeMessageHelper.setSubject("[영화 나라] 인증 번호 발급");
    // 두번째 파라미터는 html 여부
    mimeMessageHelper.setText(String.format("인증 번호는 [%d] 입니다.", mail.getAuthNumber()), false);
    mailSender.send(mimeMessage);
  }

}
