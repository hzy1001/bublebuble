package bubble.test.ex10;

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
			//System.out.println("플레이어의 위치에 따른 배경 색상확인");
		}
	}

	@Override
	public void run() {
		
		while(true){
			Color leftColor = new Color(image.getRGB(player.getX() - 10,player.getY() + 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15,player.getY() + 25));
			int bottomColor = image.getRGB(player.getX() + 10,player.getY() + 50 + 5)
							+ image.getRGB(player.getX() + 40,player.getY() + 50 + 5);
			//System.out.println(leftColor+","+rightColor);
			//System.out.println(bottomColor);
			
			
			//외벽충돌 확인
			if(leftColor.getRed()==255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				//System.out.println("왼쪽벽에 충돌1");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			}else if(rightColor.getRed()==255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				//System.out.println("오른쪽벽에 충돌1");
				player.setRightWallCrash(true);
				player.setRight(false);
			}else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//바닥충돌확인
			if(bottomColor != -2) {
				//System.out.println("바닥충돌");
				player.setDown(false);
			}else {
				if(!player.isUp() && !player.isDown()) {
					System.out.println("바닥아님");
					//player.setDown(true);
					player.down();
				}
			}
		}
	
	

	}

}
