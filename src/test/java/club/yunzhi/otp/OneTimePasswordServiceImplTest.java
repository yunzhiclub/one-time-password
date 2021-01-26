package club.yunzhi.otp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class OneTimePasswordServiceImplTest {
  private OneTimePasswordServiceImpl oneTimePasswordService;
  public OneTimePasswordServiceImplTest() {
    this.oneTimePasswordService = Mockito.spy(new OneTimePasswordServiceImpl());
  }

  @Test
  void generateOtp() {
    System.out.println(this.oneTimePasswordService.generateOtp("I Love Java"));
  }
}