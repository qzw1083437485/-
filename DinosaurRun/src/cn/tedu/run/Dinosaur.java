package cn.tedu.run;

import java.awt.image.BufferedImage;


public class Dinosaur extends Body{
	private int life=3;
	private boolean jumpState=false;            //跳跃状态
	private int jumpHeight=115;                //跳跃高度
	private int jumpValue=4;                   //跳跃初始值
	private final int LOWEST_Y=Game.HEIGHT-93;   //最低高度
	public static int  a=0;
	public int fire=10;

	public Dinosaur() {
		super(40,43,50,Game.HEIGHT-93);
	}
	public void step() {          //跳跃
		if(jumpState) {
			if(y>=LOWEST_Y) {
				jumpValue=-4;
				state=JUMP;
			}
			if(y<=LOWEST_Y-jumpHeight) {
				jumpValue=3;
				state=JUMP;
			}
			y+=jumpValue;
			if(y>=LOWEST_Y) {
				jumpState=false;
				state=LIFE;
			}
		}
	}
	public void time() {		//飞行控制
		if(state==FLY) {
			a+=1;						
		}
		if(a==500) {
			y=Game.HEIGHT-93;
			state=LIFE;
			a=0;
		}
	}

	public void jump() {     //跳跃开关
		jumpState=true;
	}

	int index=0;
	public BufferedImage getImage() {
		if(isLife()) {
			return Images.ds[index++%(Images.ds.length-2)];
		}else if(isFly()) {
			return Images.lfly[index++%Images.lfly.length];
		}
		else if(isJump()) {
			return Images.ds[30];
		}else {
			return Images.ds[31];
		}
	}
	public void Fly() {
		state=FLY;
		if(isFly()) {
			y=60;
			jumpState=false;
		}
	}

	public FireBullet shoot() {
		if(state==LIFE || state==JUMP || state==FLY) {
			if(fire>0) {
				FireBullet fb = new FireBullet(90,Game.HEIGHT-95);
				fire-=2;
				return fb;
			}else if(fire<=0) {
				return null;
			}
		}

		return null;
	}
	public int getlife(){       //恐龙的生命
		return life;
	}

	public void bddlife() {     //恐龙的生命减一
		life--; 
	}
	public void addlife(){
		life++;
	}
	public void addfire() {
		fire+=10;
	}

}
