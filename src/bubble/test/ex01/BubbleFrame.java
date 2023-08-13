package bubble.test.ex01;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;

public class BubbleFrame extends JFrame{

	public BubbleFrame() {
		// TODO Auto-generated constructor stub
		setSize(1000,640);
		getContentPane().setLayout(null);
		
		JTextArea txtrHello = new JTextArea();
		txtrHello.setText("hello");
		txtrHello.setBounds(226, 77, 182, 24);
		getContentPane().add(txtrHello);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BubbleFrame();
		

	}
}
