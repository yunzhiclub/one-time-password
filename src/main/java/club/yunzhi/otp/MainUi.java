package club.yunzhi.otp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 主UI
 *
 * @author panjie
 */
@Component
public class MainUi extends JFrame {
  /**
   * 有效时间提示
   */
  JLabel effectiveTimeLabel;
  /**
   * OTP服务
   */
  final OneTimePasswordService oneTimePasswordService;
  /**
   * 显示生成的otp验证码
   */
  JLabel otpLabel;
  /**
   * 退出按钮
   */
  JButton quitButton;
  /**
   * 提交按钮
   */
  JButton submitButton;
  /**
   * 输入文本
   */
  JTextField textField;
  /**
   * token显示
   */
  JLabel tokenLabel;

  public MainUi(OneTimePasswordService oneTimePasswordService) {
    this.oneTimePasswordService = oneTimePasswordService;
  }

  /**
   * 组件启动
   * 初始化各个组件、设置组件布局、设置基本信息
   */
  @PostConstruct
  public void bootstrap() {
    this.initLabel();
    this.initTextField();
    this.initQuitButton();
    this.initSubmitButton();
    this.createLayout();

    this.setTitle("Yunzhi OTP tool");
    this.setSize(300, 200);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  /**
   * 每1秒钟重新生成一次提示信息
   * 每1分钟重新生成一个OTP
   * 未使用@Scheduled(cron = "* 1/1 * * * *")的原因是第一个分钟的时候，未触发执行相应的方法
   */
  @Scheduled(fixedRate = 1000)
  void reGenerateOtp() {
    long second = System.currentTimeMillis() / 1000 % 60;
    StringBuilder info = new StringBuilder();
    for (long i = 0; i < 60 - second; i++) {
      info.append(".");
    }
    this.effectiveTimeLabel.setText(info.toString());

    if (second == 0 && !this.textField.getText().trim().isEmpty()) {
      this.otpLabel.setText(this.oneTimePasswordService.generateOtp(this.textField.getText().trim()));
    }
  }

  /**
   * 输入变更时，重设设置生成按钮的disabled状态
   */
  private void initTextField() {
    this.textField = new JTextField();
    this.textField.addKeyListener(new TextFieldChangeListener(() ->
        this.submitButton.setEnabled(!this.textField.getText().trim().isEmpty())
    ));
  }

  /**
   * 初始化两个label
   */
  private void initLabel() {
    this.tokenLabel = new JLabel("Token");
    this.otpLabel = new JLabel(" ");
    this.effectiveTimeLabel = new JLabel("............................................................");
  }

  /**
   * 退出按钮
   */
  private void initQuitButton() {
    this.quitButton = new JButton("Quit");
    this.quitButton.addActionListener((ActionEvent event) ->
        System.exit(0)
    );
  }

  /**
   * 点击时并显示otp
   */
  private void initSubmitButton() {
    this.submitButton = new JButton("生成OTP");
    this.submitButton.setEnabled(false);
    submitButton.addActionListener((ActionEvent event) ->
        this.otpLabel.setText(this.oneTimePasswordService.generateOtp(this.textField.getText().trim()))
    );
  }

  /**
   * 创建layout
   */
  private void createLayout() {
    Container pane = getContentPane();
    GroupLayout groupLayout = new GroupLayout(pane);
    pane.setLayout(groupLayout);

    // 自动生成间隔
    groupLayout.setAutoCreateContainerGaps(true);

    // 设置x轴方向的水平布局，相当于bootstrap中的列 ParallelGroup 向另外一轴排列 SequentialGroup 顺序排列
    // 比如当前为X轴左右排列，则ParallelGroup便是上下排列；反之亦然
    groupLayout.setHorizontalGroup(groupLayout.createParallelGroup()
                                              .addComponent(this.otpLabel)
                                              .addGroup(
                                                  groupLayout.createSequentialGroup()
                                                             .addComponent(this.tokenLabel)
                                                             .addComponent(this.textField)
                                              )
                                              .addComponent(this.effectiveTimeLabel)
                                              .addGroup(groupLayout.createSequentialGroup()
                                                                   .addGap(90)
                                                                   .addComponent(this.submitButton)
                                                                   .addComponent(this.quitButton)
                                              )
    );

    // 设置Y轴方向的布局，相当于行
    groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                                            .addComponent(this.otpLabel)
                                            .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                                 .addComponent(this.tokenLabel)
                                                                 .addComponent(this.textField))
                                            .addComponent(this.effectiveTimeLabel)
                                            .addGroup(
                                                groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                                           .addComponent(this.quitButton)
                                                           .addComponent(this.submitButton)
                                            )
    );
  }
}
