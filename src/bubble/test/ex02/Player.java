package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class Player extends JLabel{
	
	private int x;
	private int y;
	
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
		
		this.setIcon(playerR);  
		setSize(50,50);
		this.setLocation(x,y);
	}

}


