package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

//new(생성) 가능한 클래스는 추상메서드를 가질수 없다. 따라서 인터페이스를 이용하여 추상메서드를 실행하고 반드시 추상메서드를 구현해야 한다.
@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private BubbleFrame mContext;
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;

	private final int SPEED = 4;
	private final int JUMPSPEED = 3;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon playerR, playerL;

	public Player(BubbleFrame mContext) {
		// TODO Auto-generated constructor stub
		this.mContext = mContext;
		initObject();
		initSettiong();
		initBackgroundPlayerService();
	}

	private void initBackgroundPlayerService() {
		// Thread의 인자는 Runable타입만 가능(BackgroundPlayerService눈 인터페이스(부모)로 Runnable을 가지므로
		// 바로 넘길수 있다.)
		new Thread(new BackgroundPlayerService(this)).start();

	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSettiong() {

		x = 80;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		leftWallCrash = false;
		rightWallCrash = false;

		playerWay = playerWay.RIGHT;
		this.setIcon(playerR);
		setSize(50, 50);
		this.setLocation(x, y);
	}

	@Override
	public void attack() {

		new Thread(() -> {
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			if (playerWay == PlayerWay.LEFT) {
				bubble.left();
			} else {
				bubble.right();
			}
		}).start();

	}

	// 이벤트 헨들러 정의 => 각 키이벤트마다 새로운 스래드를 생성하여 처리해준다.(한스래드가 동시에 2개이상의 이벤트를 수행할수 없으므로)
	@Override
	public void left() {
		left = true;
		new Thread(() -> {
			while (left) {
				playerWay = playerWay.LEFT;
				this.setIcon(playerL);
				x = x - SPEED;
				setLocation(x, y);
				// while문 수행이 너무 빨라 쓰레드를 수행하는데 슬립을 주어서 천천이 이동하도록 한다.
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 1.이미지의 경우 모든 이벤트 루프의 이벤트가 수행이 된후 이미지기 repaint 된다.
	// 2.메인스레드만 사용하는경우 키전달을 동시에 할수없다(동시에 키두개이상 누를 수가 없다.)
	@Override
	public void right() {
		right = true;
		new Thread(() -> {
			while (right) {
				playerWay = playerWay.RIGHT;
				this.setIcon(playerR);
				x = x + SPEED;
				setLocation(x, y);

				// while문 수행이 너무 빨라 쓰레드를 수행하는데 슬립을 주어서 천천이 이동하도록 한다.
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void up() {
		up = true;
		new Thread(() -> {
			for (int i = 0; i < 130 / JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			up = false;

			down();
		}).start();

	}

	@Override
	public void down() {
		down = true;
		new Thread(() -> {
			// for (int i = 0; i < 130 / JUMPSPEED; i++) {
			while (down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			down = false;
		}).start();

	}

}
