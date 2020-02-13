package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 大敌机 */
public class BigAirplane extends FlyingObject implements Enemy {
	private int step;
	
	/** 构造方法 */
	public BigAirplane(){
		width = 66;
		height = 89;
		x = (int)(Math.random()*(World.WIDTH-this.width));
		y = -this.height;
		step = 2;
	}
	
	/** 大敌机走步step() */
	public void step(){
		y+=step;
	}
	
	int deadIndex = 1;
	public BufferedImage getImage(){
		if(isLife()){
			return Images.bigairplanes[0];
		}else if(isDead()){
			BufferedImage img = Images.bigairplanes[deadIndex++];
			if(deadIndex==Images.bigairplanes.length){
				state = REMOVE;
			}
			return img;
		}
		return null;
	}
	

	public int getScore(){
		return 3;
	}
	
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT;
	}
}





