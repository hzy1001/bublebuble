package bubble.test.ex11;

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
		//backgroundMap = new JLabel(new ImageIcon("image/backgroundMapService.png"));
		setContentPane(backgroundMap);

		player = new Player();
		this.add(player);
	}

	private void initSetting() {
		setSize(1000, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	//키클릭 이벤트 처리
	private void initListener() {
		this.addKeyListener(new KeyAdapter() {
			
			//리슨너 등록(생성)
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
				//System.out.println(e.getKeyCode());
				//메인스레드에서는 키코드에 따른 이벤트 헨들러 호출한다. (이벤트 루프(큐)에 쌓인 순서데러 이벤트를 처리해준다.)
				switch(e.getKeyCode()) { 
					case KeyEvent.VK_LEFT:
						if(!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}						
						break;
					case KeyEvent.VK_RIGHT:
						if(!player.isRight() && !player.isRightWallCrash()) {
							player.right();
						}
						break;
					case KeyEvent.VK_UP:
						if(!player.isUp() && !player.isDown()) {
							player.up();
						}
						break;
					case KeyEvent.VK_DOWN:
						//player.down();
						break;
					case KeyEvent.VK_SPACE: 
 						Bubble bubble = new Bubble(player);
						add(bubble); 
						break;						
				}
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch(e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					 player.setLeft(false);
					 break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
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
