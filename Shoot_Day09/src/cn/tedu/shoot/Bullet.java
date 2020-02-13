package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 子弹 */
public class Bullet extends FlyingObject  {
	private int step;
	
	/** 构造方法 */
	public Bullet(int x,int y){
		width = 8;
		height = 20;
		this.x = x;
		this.y = y;
		step = 3;
	}
	
	/** 子弹走步step() */
	public void step(){
		y-=step;
	}
	
	public BufferedImage getImage(){
		if(isLife()){
			return Images.bullet;
		}
		if(isDead()){
			state = REMOVE;
		}
		return null;
	}
	
	public boolean outOfBounds(){
		return this.y<=-this.height;
	}
}







