package bubble.test.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

//new(생성) 가능한 클래스는 추상메서드를 가질수 없다. 따라서 인터페이스를 이용하여 추상메서드를 실행하고 반드시 추상메서드를 구현해야 한다.
@Getter
@Setter
public class Player extends JLabel implements Moveable {

	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private ImageIcon playerR, playerL;

	public Player() {
		// TODO Auto-generated constructor stub
		initObject();
		initSettiong();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSettiong() {

		x = 55;
		y = 535;

		left = false;
		right = false;
		up = false;
		down = false;

		this.setIcon(playerR);
		setSize(50, 50);
		this.setLocation(x, y);
	}

	// 이벤트 헨들러 정의
	@Override
	public void left() {
		left = true;
		new Thread(() -> {
			while (left) {
				this.setIcon(playerL);
				x = x - 10;
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
		// 스레드 생성 방법(옛날 방법)
//		new Thread(new Runnable() { 
//			@Override
//			public void run() {  
//				this.setIcon(playerR);
//				x = x + 10;
//				setLocation(x, y); 
//			}
//		}).start(); 

		// 위의 방식을 람다식으로 하면 아래와 같이 간단하게 바꿀수 있다.
		right = true;
		new Thread(() -> {
			System.out.println("R스래드 생성");
			while (right) {
				this.setIcon(playerR);
				x = x + 10;
				setLocation(x, y);

				// while문 수행이 너무 빨라 쓰레드를 수행하는데 슬립을 주어서 천천이 이동하도록 한다.
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("R스래드 종료");
		}).start();

	}

	@Override
	public void up() {
		new Thread(new Runnable() {
			@Override
			public void run() {

			}
		}).start();

	}

	@Override
	public void down() {
		// TODO Auto-generated method stub

	}

}
