package bubble.game.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import bubble.game.compoment.Enemy;

//별도의 스래드로 동작하도록 Runnable 인터페이스를 실행한다.
public class BackgroundEnemyService implements Runnable {

	private BufferedImage image;
	private Enemy enemy; 

	//public BackgroundenemyService(BubbleFrame mContext) {
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
	 
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png"));
		} catch (Exception e) {
			// System.out.println("플레이어의 위치에 따른 배경 색상확인");
		}
	}

	@Override
	public void run() {

		while (true) {
  
			Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY() + 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY() + 25));
			int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY() + 50 + 5)
					+ image.getRGB(enemy.getX() + 40, enemy.getY() + 50 + 5);
			// System.out.println(leftColor+","+rightColor);
			// System.out.println(bottomColor);

			// 외벽충돌 확인
			if (leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) { 
				enemy.setLeft(false);
				
 				if(!enemy.isRight()) {
 					enemy.right();
 				} 
			} else if (rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
 
				enemy.setRight(false);
				
 				if(!enemy.isLeft()) {
 					enemy.left();
 				} 
			} 

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// 바닥충돌확인
			if (bottomColor != -2) {
				// System.out.println("바닥충돌");
				enemy.setDown(false);
			} else {
				if (!enemy.isUp() && !enemy.isDown()) {
					System.out.println("바닥아님");
					// enemy.setDown(true);
					enemy.down();
				}
			}
		}

	}

}
