package cn.tedu.shoot;
import java.awt.image.BufferedImage;

public class Bullet extends FlyingObject{
	private int speed;
	public Bullet(int x,int y){
		super(8,20,x,y);
		speed=3;
	}
	public void step() {
		y-=speed;
	}
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.bullet;
		}else if(isDead()) {
			state=REMOVE;
		}
		return null;
	}
	public boolean outOfBounds() {
		return this.y<=-this.height;
	}
}
