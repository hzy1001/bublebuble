package bubble.test.ex14;

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
	private BubbleFrame mContext;   //MyContext(main 함수가 있는 class) => mContext는 모든 객체의 주소값에 접근할수 있다. 따라서 이것만 있으면 Player객체도 따로 가져올 필요가 없다.

	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;

	private int state; // 0:물방울, 1:적을 가둔 물방울
	private ImageIcon bubble;
	private ImageIcon bubbled;
	private ImageIcon bomb;

	//public Bubble(Player player) {
	public Bubble(BubbleFrame mContext) {
		// TODO Auto-generated constructor stub 
		//this.player = player;
		initObject();
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		InitSetting();
		//initThread();
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

//	private void initThread() {
//		new Thread(() -> {
//			if (player.getPlayerWay() == PlayerWay.LEFT) {
//				left();
//			} else {
//				right();
//			}
//		}).start();
//	}

	@Override
	public void left() {
		left = true;
		for (int i = 0; i < 400; i++) {
			x--;
			setLocation(x, y);

			if (backgroundBubbleService.leftWall()) {
				left = false;
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
				right = false;
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
				up = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		clearBubble();
	}

	private void clearBubble() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setIcon(bomb);
		try {
			Thread.sleep(500);
			mContext.remove(this);			//메모리에서 주워준다.
			mContext.repaint();				//살아있는 메모리들만 새로 그려준다.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	

}
