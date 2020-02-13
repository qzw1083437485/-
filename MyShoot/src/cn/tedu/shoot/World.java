package cn.tedu.shoot;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Random;
import java.util.Arrays;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class World extends JPanel{

	public static final int WIDTH = 400;
	public static final int HEIGHT = 700;

	public static final int START = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int GAME_OVER = 3;
	private int state=START;

	private Sky sky = new Sky();
	private Hero hero = new Hero();
	private Bullet[] bullets = {};
	private FlyingObject[] enemies = {};


	public FlyingObject nextOne() {
		Random rand = new Random();
		int type = rand.nextInt(20);
		if(type<5) {
			return new Bee();
		}else if(type<12) {
			return new Airplane();
		}else {
			return new BigAirplane();
		}
	}

	int enterIndex=0;
	public void enterAction() {
		enterIndex++;
		if(enterIndex%40==0) {
			FlyingObject obj=nextOne();
			enemies = Arrays.copyOf(enemies, enemies.length+1);
			enemies[enemies.length-1] = obj;
		}
	}

	int shootIndex=0;
	public void shootAction() {
		shootIndex++;
		if(shootIndex%30==0) {
			Bullet[] bs = hero.shoot();
			bullets = Arrays.copyOf(bullets, bullets.length+bs.length);
			System.arraycopy(bs,0,bullets,bullets.length-bs.length,bs.length);
		}
	}


	public void stepAction() {
		sky.step();
		for(int i=0;i<enemies.length;i++) {
			enemies[i].step();
		}
		for(int i=0;i<bullets.length;i++) {
			bullets[i].step();
		}
	}

	public void outOfBoundsAction() {
		int index=0;
		FlyingObject[] enemyLives = new FlyingObject[enemies.length];
		for(int i=0;i<enemies.length;i++) {
			FlyingObject f = enemies[i];
			if(!f.outOfBounds() && !f.isRemove()) {
				enemyLives[index] = f;
				index++;
			}
		}
		enemies = Arrays.copyOf(enemyLives,index);
		index = 0;

		Bullet[] bulletLives = new Bullet[bullets.length];
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i];
			if(!b.outOfBounds() && !b.isRemove()) {
				bulletLives[index] = b;
				index++;
			}
		}
		bullets = Arrays.copyOf(bulletLives, index);
		index = 0;
	}

	int score=0;
	public void bulletBangAction() {
		for(int i=0;i<bullets.length;i++) {
			Bullet b = bullets[i];
			for(int j=0;j<enemies.length;j++) {
				FlyingObject f = enemies[j];
				if(f.isLife() && b.isLife() && f.hit(b)) {
					b.goDead();
					f.goDead();
					if(f instanceof Enemy) {
						Enemy e = (Enemy)f;
						score+=e.getScore();
					}
					if(f instanceof Award) {
						Award a = (Award)f;
						int type = a.getAwardType();
						switch(type) {
						case Award.FIRE:
							hero.addFire();
							break;
						case Award.LIFE:
							hero.addLife();
							break;
						}
					}
				}
			}
		}
	}
	public void heroBangAction() {
		for(int i=0;i<enemies.length;i++) {
			FlyingObject f = enemies[i];
			if(f.isLife() && hero.isLife() &&f.hit(hero)) {
				f.goDead();
				hero.clearFire();
				hero.subtractLife();
			}
		}
	}
	public void checkGameOverAction() {
		if(hero.getLife()<=0) {
			state=GAME_OVER;
		}
	}
	public void action() {
		MouseAdapter l = new MouseAdapter() {
			public void mouseMoved(MouseEvent e){
				if(state==RUNNING) {
					int x = e.getX();
					int y = e.getY();
					hero.moveTo(x, y);
				}
			}
			public void mouseClicked(MouseEvent e){
				switch(state) {
				case START:
					state=RUNNING;
					break;
				case GAME_OVER:
					score=0;
					sky = new Sky();
					hero = new Hero();
					enemies = new FlyingObject[0];
					bullets = new Bullet[0];
					state=START;
					break;
				}
			}
			public void mouseExited(MouseEvent e){
				if(state==RUNNING) {
					state=PAUSE;
				}
			}
			public void mouseEntered(MouseEvent e){
				if(state==PAUSE) {
					state=RUNNING;
				}
			}
		};
		this.addMouseListener(l);
		this.addMouseMotionListener(l);

		Timer timer = new Timer();
		int intervel = 10;
		timer.schedule(new TimerTask() {
			public void run() {
				if(state==RUNNING) {
					enterAction();
					shootAction();
					stepAction();
					outOfBoundsAction();
					bulletBangAction();
					heroBangAction();
					checkGameOverAction();
				}
				repaint();
			}
		},intervel,intervel);
	}


	public void paint(Graphics g) {
		sky.paintObject(g);
		hero.paintObject(g);
		for(int i=0;i<enemies.length;i++) {
			enemies[i].paintObject(g);
		}
		for(int i=0;i<bullets.length;i++) {
			bullets[i].paintObject(g);
		}
		g.drawString("SCORE:"+score,10,25);
		g.drawString("LIFE:"+hero.getLife(),10,45);

		switch(state) {
		case START:
			g.drawImage(Images.start,0,0,null);
			break;
		case PAUSE:
			g.drawImage(Images.pause,0,0,null);
			break;
		case GAME_OVER:
			g.drawImage(Images.gameover,0,0,null);
			break;
		}
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		World world = new World();
		frame.add(world);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true); 

		world.action();


	}

}
