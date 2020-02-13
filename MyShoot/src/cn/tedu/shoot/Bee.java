package cn.tedu.shoot;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Bee extends FlyingObject implements Award{
	private int xSpeed;
	private int ySpeed;
	private int awardType;
	public Bee(){
		super(60,51);
		xSpeed=2;
		ySpeed=2;
		Random rand = new Random();
		awardType=rand.nextInt(2);
	}
	public void step() {
		x+= xSpeed;
		y+= ySpeed;
		if(x<=0 || x>=World.WIDTH-this.width) {
			xSpeed*=-1;
			
		}
	}
	int index=1;
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.bees[0];
		}else if(isDead()){
			BufferedImage img = Images.bees[index++];
			if(index==Images.bees.length) {
				state=REMOVE;
			}
			return img;
		}
		return null;
	}
	public int getAwardType() {
		return awardType;
	}
}
