package cn.tedu.run;

import java.awt.image.BufferedImage;

public class FireBullet extends Body{
	private int speed;
	
	public FireBullet(int x,int y) {
		super(35,20,x,y);
		speed=3;
	}
	public void step() {
		x+=speed;
	}
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.firebullet;
		}else {
			return null;
		}
		
	}
	public boolean outOfBounds() {
		return this.x<=-this.width;
	}
}
