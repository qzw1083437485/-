package cn.tedu.run;

import java.awt.image.BufferedImage;

public class BigBarrier extends Body{
	private int speed;

	public BigBarrier() {
		super(26,48,Game.WIDTH,Game.HEIGHT-48-50);
		speed=4;
	}
	public void step() {
		x-=speed;
	}
	
	public  BufferedImage getImage(){
		if(isLife()) {
			return Images.bigbarrier;
		}else {
			return null;
		}
	}

}
