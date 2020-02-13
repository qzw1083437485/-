package cn.tedu.run;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Sky extends Body{
	private int speed;
	private int x1;

	public Sky() {
		super(Game.WIDTH,Game.HEIGHT,0,-20);
		speed=4;
		x1=Game.WIDTH;
	}
	public void step() {
		x-=speed;
		x1-=speed;
		if(x<=-Game.WIDTH) {
			x=Game.WIDTH;
		}
		if(x1<=-Game.WIDTH) {
			x1=Game.WIDTH;
		}
	}
	
	public  BufferedImage getImage(){
			return Images.sky;
	}
	public void paintObject(Graphics g) {
		g.drawImage(getImage(),x,y,null);
		g.drawImage(getImage(),x1,y,null);
	}

}
