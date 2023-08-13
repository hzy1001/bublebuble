package bubble.test.ex03;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//new(생성) 가능한 클래스는 추상메서드를 가질수 없다. 따라서 인터페이스를 이용하여 추상메서드를 실행하고 반드시 추상메서드를 구현해야 한다.
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

	//이벤트 헨들러 정의
	@Override
	public void left() {
		this.setIcon(playerL);
		x = x - 10;
		setLocation(x, y);
	}
	
	//1.이미지의 경우 모든 이벤트 루프의 이벤트가 수행이 된후 이미지기 repaint 된다.
	//2.메인스레드만 사용하는경우 키전달을 동시에 할수없다(동시에 키두개이상 누를 수가 없다.)
	@Override
	public void right() {
		//try {
			//Thread.sleep(100);  
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		this.setIcon(playerR);
		x = x + 10;
		setLocation(x, y);

	}

	@Override
	public void up() {
		// TODO Auto-generated method stub

	}

	@Override
	public void down() {
		// TODO Auto-generated method stub

	}

}
