package cn.tedu.run;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Fly extends Reward implements Flying{
	private int speed;
	int award=0;

	public Fly(){
		super(32,22);
		speed=2;
	}
	public void step(){
		x-=speed;
	}
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.fly;
		}
		return null;
	}
}
