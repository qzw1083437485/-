package cn.tedu.run;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Life extends Reward implements Award{
	private int speed;
	Random  rand=new Random();
	int award=rand.nextInt(2);
	
	public Life(){
		super(28,40);
		speed=2;
	}
	public void step(){
		x-=speed;
	}
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.Life;
		}
			return null;
			}
	public int Award(){
		return award;
	}
}
