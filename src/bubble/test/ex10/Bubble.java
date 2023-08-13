package bubble.test.ex10;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

//import bubble.test.ex10.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel {

	// 의존성 컴포지션
	private Player player;

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

	}
}
