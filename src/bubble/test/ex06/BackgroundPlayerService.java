package bubble.test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

//별도의 스래드로 동작하도록 Runnable 인터페이스를 실행한다.
public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		
		// TODO Auto-generated constructor stub
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			System.out.println("플레이어의 위치에 따른 배경 색상확인");
		}
	}

	@Override
	public void run() {
		
		while(true){
			Color leftColor = new Color(image.getRGB(player.getX() - 10,player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15,player.getY() + 25));
			//System.out.println(leftColor+","+rightColor);
			
			if(leftColor.getRed()==255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				System.out.println("왼쪽벽에 충돌");
			}else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				System.out.println("오른쪽벽에 충돌");
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	

	}

}
