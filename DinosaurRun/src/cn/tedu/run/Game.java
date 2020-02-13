package cn.tedu.run;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;
import java.awt.event.MouseEvent;


public class Game extends JPanel{
	public static final int WIDTH=734;
	public static final int HEIGHT=286;

	public static final int START=0;
	public static final int RUNNING=1;
	public static final int PAUSE=2;
	public static final int GAME_OVER=3;
	private int state = START;

	private Sky sky=new Sky();
	private Dinosaur d=new Dinosaur();
	private FireBullet[] fbs = {};
	private Body[] enemies={};
	private Reward[] r= {};

	Sound hitSound;
	Sound flappySound;
	Sound scoreSound;
	Sound startSound;



	public Body nextOne() {        //产生障碍物
		Random rand=new Random();
		int type=rand.nextInt(20);
		if(type<5) {
			return new Bird();
		}else if(type<12) {
			return new Barrier();
		}else {
			return new BigBarrier();		
		}
	}

	public Reward nextOne1() {        //产生奖励类型
		Random rand=new Random();
		int type=rand.nextInt(20);
		if(type<10) {
			return new Life();
		}else if(type<15){
			return new Fly();		
		}else {
			return new Fire();
		}
	}

	int reIndex=0;
	public void reAction() {  //奖励入场
		reIndex++;
		if(reIndex%800==0) {
			Reward reward=nextOne1();
			r=Arrays.copyOf(r, r.length+1);
			r[r.length-1]=reward;
		}
	}


	int enterIndex=0;
	public void enterAction() {  //障碍物入场
		enterIndex++;
		if(enterIndex%150==0) {
			Body body=nextOne();
			enemies=Arrays.copyOf(enemies, enemies.length+1);
			enemies[enemies.length-1]=body;
		}
	}

	public void step1Action() {    //奖励移动
		for(int i=0;i<r.length;i++) {
			r[i].step();
		}
	}

	public void stepAction() {            //对象移动
		d.step();
		sky.step();
		for(int i=0;i<enemies.length;i++) {
			enemies[i].step();
		}
		for(int i=0;i<fbs.length;i++) {
			fbs[i].step();
		}
	}

	public void outOfBounds1Action() {      //删除越界奖励
		int index=0;
		Reward [] reLives=new Reward[r.length];
		for(int i=0;i<r.length;i++) {
			Reward f=r[i];
			if(!f.outOfBounds()&& !f.isDead()) {
				reLives[index]=f;
				index++;
			}
		}
		r=Arrays.copyOf(reLives, index);   
	}


	public void outOfBoundsAction() {      //删除越界障碍物
		int index=0;
		Body [] enemyLives=new Body[enemies.length];
		for(int i=0;i<enemies.length;i++) {
			Body f=enemies[i];if(index>20) {
				state=RUNNING;
			}
			if(!f.outOfBounds()&& !f.isDead()) {
				enemyLives[index]=f;
				index++;
			}
		}
		enemies=Arrays.copyOf(enemyLives, index);   
	}
	public void outbulletOfBoundsAction() { 
		int index=0;
		FireBullet [] fbl= new FireBullet[fbs.length];
		for(int i=0;i<fbs.length;i++) {
			FireBullet f =fbs[i];
			if(index>20) {
				state=RUNNING;
			}
			if(!f.outbulletBounds()&& !f.isDead()) {
				fbl[index]=f;
				index++;
			}
		}
	}

	public void bodyBangAction() {           //恐龙与障碍物碰撞
		for(int i=0;i<enemies.length;i++) {
			Body b=enemies[i];
			if(b.isLife()&&!d.isDead()&&b.hit(d)) {
				b.goDead();
				d.bddlife();		
			}
		}
	}

	public void rewardBangAction() {     //恐龙与奖励相撞
		for(int i=0;i<r.length;i++) {
			Reward re=r[i];
			if(re.isLife()&&!d.isDead()&&re.hit(d)) {
				re.goDead();
				if(re instanceof Award){
					Award a=(Award)re;
					int type=a.Award();
					switch(type){
					case Award.life:
						d.addlife();
						break;
					case Award.score:
						score+=20;
						break;
					}
				}
				if(re instanceof Flying) {     //恐龙与飞行物相撞
					d.Fly();
				}
				if(re instanceof Spitfire) {  //恐龙与火球相撞
					d.addfire();
				}
			}
		}}
	public void goGameOver() {
		if(d.getlife()<=0) {
			d.goDead();              //恐龙死亡
			state=GAME_OVER;         //游戏结束
		}
	}

	double score=0;
	public void addscore() {       //计分系统
		score+=0.01;
	}
	int hiscore=0;
	public void addhiscore() {     //最高分系统
		if(score>=hiscore) {
			hiscore=(int)score;
		}
	}
	int shootIndex=0;
	public void shootAction() {    //火球入场
		if(d.fire>0) {
			if((fbs.length)%5==0) {
				shootIndex+=80;
			}
			shootIndex++;
			if(shootIndex%90==0) {
				FireBullet fb = d.shoot();
				fbs = Arrays.copyOf(fbs,fbs.length+1);
				fbs[fbs.length-1] =fb; 
			}
		}
	}
	public void bulletBangAction() {   //子弹与障碍物碰撞
		for(int i=0;i<fbs.length;i++) {
			FireBullet f= fbs[i];
			for(int j=0;j<enemies.length;j++) {
				Body b = enemies[j];
				if(f.isLife() && b.isLife() && f.hit(b)) {
					b.goDead();
					f.goDead();
				}
			}
		}
	}
	public void action() {
		MouseAdapter l = new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				switch(state) {
				case START:
					state=RUNNING;
					break;
				case GAME_OVER:
					r=new Reward[0];
					d=new Dinosaur();
					sky=new Sky();
					enemies=new Body[0];
					score=0;
					state=START;
					break;
				}
				if(state==RUNNING&&!d.isFly()) {     //鼠标点击改变跳跃状态
					d.jump();
				}
			}
		};
		this.addMouseListener(l);        //处理鼠标操作    
		this.addMouseMotionListener(l);  //处理鼠标滑动 
		Timer timer = new Timer();      //定时器
		int intervel = 10;
		timer.schedule(new TimerTask() {  
			public void run() {
				if(state==RUNNING) {
					rewardBangAction();
					step1Action();
					reAction();
					shootAction();
					outOfBounds1Action();
					bodyBangAction();
					bulletBangAction();
					outbulletOfBoundsAction();
					outOfBoundsAction(); 
					stepAction();
					enterAction();
					goGameOver();
					addscore();
					addhiscore();
					d.time();
				}
				repaint();  
			}
		},intervel,intervel);
	}


	public void paint(Graphics g) {    //画对象
		sky.paintObject(g);
		d.paintObject(g);
		for(int i=0;i<fbs.length;i++) {
			fbs[i].paintObject(g);
		}
		for(int i=0;i<enemies.length;i++) {
			enemies[i].paintObject(g);
		}
		for(int i=0;i<r.length;i++) {
			r[i].paintObject(g);
		}

		g.drawString("LIFE:"+d.getlife(), 670, 30);
		g.drawString("SCORE:"+(int)score, 600, 30);
		g.drawString("HISCORE:"+(int)hiscore, 530, 30);

		switch(state) {
		case START:
			g.drawImage(Images.start,230,50,null);
			break;
		case GAME_OVER:
			g.drawImage(Images.gameover,230,50,null);
			break;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Game game = new Game();
		frame.add(game);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		game.action();
	}
}
