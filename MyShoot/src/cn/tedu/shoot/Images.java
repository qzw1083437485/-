package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class Images {
	public static BufferedImage sky;
	public static BufferedImage bullet;
	public static BufferedImage[] heros;
	public static BufferedImage[] airplane;
	public static BufferedImage[] bees;
	public static BufferedImage[] bigairplane;
	
	public static BufferedImage start;
	public static BufferedImage pause;
	public static BufferedImage gameover;

	static {//初始化静态资源
		sky = readImage("background.png");
		bullet = readImage("bullet.png");
		heros = new BufferedImage[2];
		heros[0] = readImage("hero0.png");
		heros[1] = readImage("hero1.png");

		airplane = new BufferedImage[5];
		bigairplane = new BufferedImage[5];
		bees = new BufferedImage[5];
		airplane[0] = readImage("airplane0.png");
		bigairplane[0] = readImage("bigairplane0.png");
		bees[0] = readImage("bee0.png");
		for(int i=1;i<airplane.length;i++) {
			airplane[i] = readImage("bom"+i+".png");
			bigairplane[i] = readImage("bom"+i+".png");
			bees[i] = readImage("bom"+i+".png");
		}
		
		start = readImage("start.png");
		pause = readImage("pause.png");
		gameover = readImage("gameover.png");
	}
	public static BufferedImage readImage(String fileName) {
		try{
			BufferedImage img = ImageIO.read(FlyingObject.class.getResource(fileName));
			return img;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
