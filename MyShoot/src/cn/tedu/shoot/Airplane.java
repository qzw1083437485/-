package cn.tedu.shoot;

import java.awt.image.BufferedImage;

public class Airplane extends FlyingObject implements Enemy{
	private int speed;
	Airplane(){
		super(48,50);
		speed = 2;
	}
	public void step() {
		y+=speed;
	}
	int index=1;
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.airplane[0];
		}else if(isDead()){
			BufferedImage img = Images.airplane[index++];
			if(index==Images.airplane.length) {
				state=REMOVE;
			}
			return img;
		}
		return null;
	}
	/*
	 *                     index=1;
	 * 10m img=airplane[1] index=2         返回img=ariplane[1];
	 * 20m img=airplane[2] index=3         返回img=airplane[2]
	 * 30m img=airplane[3] index=4         返回img=airplane[3];
	 * 40m img=airplane[4] index=5(REMOVE) 返回img=airplane[4];
	 * 50m 
	 */


	public int getScore() {
		return 1;//打掉小敌机，玩家得一分
	}

}
