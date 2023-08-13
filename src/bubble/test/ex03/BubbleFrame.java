package bubble.test.ex03;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;
	private Player player;

	public BubbleFrame() {

		initObject();
		initSetting();
		initListener();
		setVisible(true);
	}

	private void initObject() {
		// backgroundMap = new JLabel("Hello");
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);

		player = new Player();
		this.add(player);
	}

	private void initSetting() {
		setSize(1000, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	//이벤트 리스터 생성
	private void initListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				System.out.println(e.getKeyCode());
				
				switch(e.getKeyCode()) { 
					case KeyEvent.VK_LEFT:
						player.left();
						break;
					case KeyEvent.VK_RIGHT:
						player.right();
						break;
					case KeyEvent.VK_UP:
						player.up();
						break;
					case KeyEvent.VK_DOWN:
						//player.down();
						break;
				}
				
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BubbleFrame();
	}
}
