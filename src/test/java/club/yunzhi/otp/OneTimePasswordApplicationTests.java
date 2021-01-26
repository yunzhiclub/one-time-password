package club.yunzhi.otp;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OneTimePasswordApplicationTests {

  /**
   * 避免 .headless(false) 后出现的 java.awt.HeadlessException
   */
  @BeforeAll
  public static void beforeAll() {
    System.setProperty("java.awt.headless", "false");
  }

  @Test
  void contextLoads() {
  }
}
