package cn.tedu.shoot;
import java.awt.image.BufferedImage;
/** 英雄机 */
public class Hero extends FlyingObject  {
	private int life; //命
	private int doubleFire; //火力值
	
	/** 构造方法 */
	public Hero(){
		width = 97;
		height = 139;
		x = 140;
		y = 400;
		life = 3;
		doubleFire = 1000000;
	}
	
	public void step(){
	}
	
	/** 英雄机随着鼠标动   x:鼠标的x坐标  y:鼠标的y坐标 */
	public void moveTo(int x,int y){
		this.x = x-this.width/2;
		this.y = y-this.height/2;
	}
	
	private int index = 0;
	public BufferedImage getImage(){
		if(isLife()){
			return Images.heros[index++%Images.heros.length];
		}
		return null;
	}
	
	public Bullet[] shoot(){
		int xStep = this.width/4;
		int yStep = 20;
		if(doubleFire>0){
			Bullet[] bs = new Bullet[2];
			bs[0] = new Bullet(this.x+1*xStep,this.y-yStep);
			bs[1] = new Bullet(this.x+3*xStep,this.y-yStep);
			doubleFire-=2;
			return bs;
		}else{
			Bullet[] bs = new Bullet[1];
			bs[0] = new Bullet(this.x+2*xStep,this.y-yStep);
			return bs;
		}
	}
	
	public void addLife(){
		life++;
	}
	
	public void addDoubleFire(){
		doubleFire+=40;
	}
	
	public int getLife(){
		return life;
	}
	
	public void subtractLife(){
		life--;
	}
	
	public int getDoubleFire(){
		return doubleFire;
	}
	
	public void clearDoubleFire(){
		doubleFire = 0;
	}
	
	public boolean outOfBounds(){
		return false;
	}
	
}








