package club.yunzhi.otp;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * 一次性密码
 *
 * @author panjie
 */
@Service
public class OneTimePasswordServiceImpl implements OneTimePasswordService {

  /**
   * 生成OTP
   * 1. 获取当前整分的时间戳
   * 2. 根据时间戳与token接拼
   * 3. md5、sha1后取后四位做为验证码
   *
   * @param token token
   * @return 4位验证码
   */
  @Override
  public String generateOtp(String token) {
    long currentMinuteTimestamp = System.currentTimeMillis() / (1000 * 60);
    token = token + currentMinuteTimestamp;
    String md5Hex = DigestUtils.md5DigestAsHex(token.getBytes(StandardCharsets.UTF_8));
    String sha1 = DigestUtils.md5DigestAsHex(md5Hex.getBytes(StandardCharsets.UTF_8)).toUpperCase();
    return sha1.substring(sha1.length() - 6);
  }
}
