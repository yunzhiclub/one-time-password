package club.yunzhi.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * 一次性密码 使用JFrame展示
 *
 * @author panjie
 */
@SpringBootApplication
public class OneTimePasswordApplication {
  /**
   * 启动方法
   * 在这里我们要使用JFrame显示UI，不能够使用 SpringApplication.run() 方法
   * 改用以下方法，设置headless为false
   *
   * @param args 接收参数
   */
  public static void main(String[] args) {

    new SpringApplicationBuilder(OneTimePasswordApplication.class)
        .headless(false).run(args);
  }
}
