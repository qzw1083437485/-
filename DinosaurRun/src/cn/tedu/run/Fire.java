package cn.tedu.run;

import java.awt.image.BufferedImage;

public class Fire extends Reward implements Spitfire{
	private int speed;
	int award=0;

	public Fire(){
		super(30,45);
		speed=2;
	}
	public void step(){
		x-=speed;
	}
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.fire;
		}
		return null;
	}
}
