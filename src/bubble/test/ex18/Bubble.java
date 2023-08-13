package bubble.test.ex18;

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
	private Enemy enemy;
	private BackgroundBubbleService backgroundBubbleService;
	private BubbleFrame mContext; // MyContext(main 함수가 있는 class) => mContext는 모든 객체의 주소값에 접근할수 있다. 따라서 이것만 있으면
									// Player객체도 따로 가져올 필요가 없다.

	private int x;
	private int y;

	private boolean left;
	private boolean right;
	private boolean up;

	private int state; // 0:물방울, 1:적을 가둔 물방울
	private ImageIcon bubble;
	private ImageIcon bubbled;
	private ImageIcon bomb;

	// public Bubble(Player player) {
	public Bubble(BubbleFrame mContext) {
		// TODO Auto-generated constructor stub
		// this.player = player;
		initObject();
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		InitSetting();
		// initThread();
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

			if ((Math.abs(x - enemy.getX()) > 40 && Math.abs(x - enemy.getX()) < 60) && Math.abs(y - enemy.getY()) > 0
					&& Math.abs(y - enemy.getY()) < 50) {
				// if(x - enemy.getX() < 10) {
				System.out.println("적과충돌");
			    //적이 살아있는상태(방울안에갖히지않은상태)일경우만 공격가능
				if(enemy.getState()==0) {
					attack();
					break;
				} 
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
			if ((Math.abs(x - enemy.getX()) < 10) && 
					Math.abs(y - enemy.getY()) > 0 && Math.abs(y - enemy.getY()) < 50) {
				// if(x - enemy.getX() < 10) {
				System.out.println("적과충돌");
			    //적이 살아있는상태(방울안에갖히지않은상태)일경우만 공격가능
				if(enemy.getState()==0) {
					attack();
					break;
				} 
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

				if (state == 0) {
					Thread.sleep(1);
				} else {
					Thread.sleep(10);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			//버블이 적을 안맞춘경우만 자동 클리어!
			if(state==0) {
				clearBubble();
			}
		}

	@Override
	public void attack() {
		state = 1; // 적을 맞춤
		enemy.setState(1);
		setIcon(bubbled);
		mContext.remove(enemy); // 메모리에서 주워준다.=> 그러나 가비지 컬렉션이 메모리 소멸되는데 시간차가 있다. 애너미의 버블드 상태를 하나 더준다.
		mContext.repaint(); // 살아있는 메모리들만 새로 그려준다. 

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
			mContext.remove(this); // 메모리에서 주워준다.
			mContext.repaint(); // 살아있는 메모리들만 새로 그려준다.
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void clearBubbled() {
		new Thread(()->{
			try {
				up = false;
				setIcon(bomb);
				Thread.sleep(1000);
				mContext.remove(this);
				mContext.repaint();					//그림이 다시그려주는 순간 모든 객체가 지워졌다 다시생성되므로 clearBubbled 다음 벽충돌 체크시 에러발생되므로 별도의 스래드로 실행하도록 한다.
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}).start();

	}

}
