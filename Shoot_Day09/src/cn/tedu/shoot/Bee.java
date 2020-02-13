package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 小蜜蜂 */
public class Bee extends FlyingObject implements Award {
	private int xStep; //x坐标走步
	private int yStep; //y坐标走步
	private int awardType; //奖励类型
	
	/** 构造方法 */
	public Bee(){
		width = 60;
		height = 51;
		x = (int)(Math.random()*(World.WIDTH-this.width));
		y = -this.height;
		xStep = 1;
		yStep = 2;
		awardType = (int)(Math.random()*2);
	}
	
	/** 蜜蜂走步step() */
	public void step(){
		x+=xStep;
		y+=yStep;
		if(x>=World.WIDTH-this.width || x<=0){
			xStep*=-1;
		}
	}

	int deadIndex = 1;
	public BufferedImage getImage(){
		if(isLife()){
			return Images.bees[0];
		}else if(isDead()){
			BufferedImage img = Images.bees[deadIndex++];
			if(deadIndex==Images.bees.length){
				state = REMOVE;
			}
			return img;
		}
		return null;
	}
	
	public int getType(){
		return awardType;
	}
	
	public boolean outOfBounds(){
		return this.y>=World.HEIGHT;
	}
}










