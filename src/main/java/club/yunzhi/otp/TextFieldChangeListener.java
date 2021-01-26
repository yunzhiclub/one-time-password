package club.yunzhi.otp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 文件输入框监听器
 * @author panjie
 */
public class TextFieldChangeListener implements KeyListener {
  private final KeyReleasedCallback keyReleasedCallback;

  public TextFieldChangeListener(KeyReleasedCallback keyReleasedCallback) {
    this.keyReleasedCallback = keyReleasedCallback;
  }

  @Override
  public void keyTyped(KeyEvent e) {
  }

  @Override
  public void keyPressed(KeyEvent e) {
  }

  @Override
  public void keyReleased(KeyEvent e) {
    this.keyReleasedCallback.keyReleased();
  }
}
