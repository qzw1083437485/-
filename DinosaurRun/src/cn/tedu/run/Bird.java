package cn.tedu.run;

import java.awt.image.BufferedImage;


public class Bird extends Body{
	private int speed;

	public Bird() {
		super(38,23);
		speed=4;
	}
	public void step() {
		x-=speed;
	}
	
	int index=0;
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.birds[index++%Images.birds.length];
		}
		return null;
	}

}
