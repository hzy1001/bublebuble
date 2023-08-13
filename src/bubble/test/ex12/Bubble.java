package bubble.test.ex12;

import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable {

	// 의존성 컴포지션
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;

	private int state; // 0:물방울, 1:적을 가둔 물방울
	private ImageIcon bubble;
	private ImageIcon bubbled;
	private ImageIcon bomb;

	public Bubble(Player player) {
		// TODO Auto-generated constructor stub
		this.player = player;
		initObject();
		InitSetting();
		initThread();
	}

	private void InitSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();
		state = 0;

		this.setIcon(bubble);
		setSize(50, 50);
		this.setLocation(x, y);

	}

	private void initObject() {
		// TODO Auto-generated method stub
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");

		backgroundBubbleService = new BackgroundBubbleService(this);

	}

	private void initThread() {
		new Thread(() -> {
			if (player.getPlayerWay() == PlayerWay.LEFT) {
				left();
			} else {
				right();
			}
		}).start();
	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);

			if (backgroundBubbleService.leftWall()) {
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++;
			setLocation(x, y);

			if (backgroundBubbleService.rightWall()) {
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--;
			setLocation(x, y);

			if (backgroundBubbleService.topWall()) {
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
