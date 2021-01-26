package club.yunzhi.otp;

/**
 * 一次性密码
 *
 * @author panjie
 */
public interface OneTimePasswordService {
  /**
   * 生成Otp
   *
   * @param token token
   * @return 生成一次性密码
   */
  String generateOtp(String token);
}
