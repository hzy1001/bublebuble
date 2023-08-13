package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame{

	private JLabel backgroundMap; 
	private Player player;
	
	public BubbleFrame() {

		initObject();
		initSetting();
		setVisible(true);
	}
	
	private void initObject() {
		//backgroundMap = new JLabel("Hello");
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);
//		backgroundMap.setSize(1000,600);
//		backgroundMap.setLocation(0,0);	 
//		getContentPane().add(backgroundMap);
		
		player = new Player();
		this.add(player);
	}
	
	private void initSetting() {
		setSize(1000,640);
//		getContentPane().setLayout(null); 
		
//		JLabel lblNewLabel = new JLabel("안녕");
//		lblNewLabel.setBounds(12, 10, 57, 15);
//		getContentPane().add(lblNewLabel);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BubbleFrame();
	}
}
