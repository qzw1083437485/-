package cn.tedu.run;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Images {
	public static BufferedImage [] ds;
	public static BufferedImage [] birds;
	public static BufferedImage  sky;
	public static BufferedImage  barrier;
	public static BufferedImage  bigbarrier;
	public static BufferedImage Life;
	public static BufferedImage fly;
	public static BufferedImage fire;
	public static BufferedImage firebullet;
	public static BufferedImage[] lfly;
	
	public static BufferedImage start;
	public static BufferedImage gameover;

static {
	sky=readImage("map.png");
	barrier=readImage("cactus02.png");
	bigbarrier=readImage("cactus03.png");
	fire=readImage("fire.png");
	firebullet=readImage("fireBullet.png");
	
	
	ds =new BufferedImage[32];
	for(int i=0;i<15;i++) {
		ds[i]=readImage("long1.png");
	}
	for(int i=15;i<30;i++) {
		ds[i]=readImage("long2.png");
	}
	ds[30]=readImage("long3.png");
	ds[31]=readImage("over.png");
	
	birds=new BufferedImage[30];
	for(int i=0;i<15;i++) {
		birds[i]=readImage("bird1.png");
	}
	for(int i=15;i<30;i++) {
		birds[i]=readImage("bird2.png");
	}
	lfly = new BufferedImage[2];
	lfly[0] = readImage("longfly.png");
	lfly[1] = readImage("longfly02.png");
	Life=readImage("life.png");
	fly=readImage("xingxing.png");
	start=readImage("game_start.png");
	gameover=readImage("game_over.png");
}
	
	public static BufferedImage readImage (String fileName) {
		try{
			BufferedImage img = ImageIO.read(Body.class.getResource(fileName));
			return img;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
