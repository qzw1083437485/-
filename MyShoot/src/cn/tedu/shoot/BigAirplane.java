package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class BigAirplane extends FlyingObject implements Enemy{
	private int speed;
	public BigAirplane(){
		super(66,89);
		speed = 2;
	}
	public void step() {
		y+=speed;
	}
	int index=1;
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.bigairplane[0];
		}else if(isDead()){
			BufferedImage img = Images.bigairplane[index++];
			if(index==Images.bigairplane.length) {
				state=REMOVE;
			}
			return img;
		}
		return null;
	}
	public int getScore() {
		return 3;
	}
}
