package cn.tedu.run;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;


public class Body {
	public static final int LIFE=0;
	public static final int DEAD=1;
	public static final int JUMP=2;
	public static final int FLY=3;
	protected int state=LIFE;
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	
	public Body(int width,int height,int x,int y) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}
	public Body(int width,int height) {
		this.width=width;
		this.height=height;
		Random rand=new Random();
		this.x=Game.WIDTH;
		this.y=rand.nextInt(Game.HEIGHT/2-this.height-50)+Game.HEIGHT/2;
	}
	
	public boolean isLife() {
		return state==LIFE;
	}
	public boolean isDead() {
		return state==DEAD;
	}
	public boolean isJump() {
		return state==JUMP;
	}
	public boolean isFly() {
		return state==FLY;
	}
	
	public void step() {                //对象移动
	}
	public  BufferedImage getImage(){       //得到对象图片
		return null;
	}
	public void paintObject(Graphics g) {    //画对象
		g.drawImage(getImage(),x,y,null);
	}
	
	public boolean outOfBounds() {      //判断障碍物是否越界
		return this.x<=0-this.width;
	}
	public boolean outbulletBounds() {
		return this.x>=Game.WIDTH;
	}
	public boolean hit(Body other) {    //判断障碍物是否碰撞
		int x1=this.x-other.width;
		int x2=this.x+this.width;
		int y1=this.y-other.height;
		int y2=this.y+this.height;
		int x=other.x;
		int y=other.y;
		return x>=x1&&x<=x2&&y>=y1&&y<=y2;
	}
	
	public void goDead() {      //障碍物去死
		state=DEAD;
	}

}
