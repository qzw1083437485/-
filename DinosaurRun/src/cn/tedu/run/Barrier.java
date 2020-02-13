package cn.tedu.run;

import java.awt.image.BufferedImage;


public class Barrier extends Body{
	private int speed;
	
	public Barrier() {
		super(21,39,Game.WIDTH,Game.HEIGHT-39-50);
		speed=4;
	}
	public void step() {
		x-=speed;
	}
	public  BufferedImage getImage(){
		if(isLife()) {
			return Images.barrier;
		}else {
			return null;
		}
	}

}
