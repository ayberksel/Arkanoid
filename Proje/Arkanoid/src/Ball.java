import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class Ball {
	
	//Fields
	private double x, y, dx, dy;
	private int ballSize = 30;
	private Image ballImg = null;
	private int damageValue;
	private long powerLostTimer;
	public static boolean ballPowerLost;
	
	//Constructor
	public Ball() {
		
		init();
		
	}
	
	public void init() {
		
		x	= ArkanoidMain.WIDTH / 2 - ballSize / 2;
		y 	= ArkanoidMain.HEIGHT - 250;
		
		if(MenuPanel1.difficulty == "Novice") {
			dx 	= 1;
			dy 	= 3;
		}
		
		else if(MenuPanel1.difficulty == "Intermediate") {
			dx 	= 2;
			dy 	= 6;
		}
		
		else if(MenuPanel1.difficulty == "Advanced") {
			dx 	= 3;
			dy 	= 9;
		}
		
		ballPowerLost = false;
		damageValue = 1;

		ballImg = getImage("/Images/Balls/ball1.gif");
	}
	
	public void update() {
		
		setPosition();
	
		if((System.nanoTime() - powerLostTimer) / 1000 > 12000000) {
			ballPowerLost = false;
		}
		
	}
	
	public void setPosition() {
		
		x	+= dx;
		y	+= dy;
		
		//Controls the ball to stay in the edges
		if(x < 0) {
			dx	= -dx;
		}
		
		if(y < 0) {
			dy	= -dy;
		}
		
		if(x > ArkanoidMain.WIDTH - ballSize) {
			dx	= -dx;
		}
		
		if(y > ArkanoidMain.HEIGHT - ballSize) {
			dy	= -dy;
		}
	}
	
	public void draw(Graphics2D g) {

		g.drawImage(ballImg, (int)x, (int)y, ballSize, ballSize, null);

		if(ballPowerLost) {			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.PLAIN, 25));
			g.drawString("Energy: " + (12 - ((System.nanoTime() - powerLostTimer) / 1000000000)), 750, 30);
		}
		
	}
	
	public Image getImage(String path) {
		
		Image tempImage = null;
		
		try {
			URL imageURL = Ball.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return tempImage;
		
	}
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, ballSize, ballSize);
	}
	
	public boolean lose() {
		
		boolean lose = false;
		
		if(Paddle.shieldActive) {
			lose = false;
		}
		else {
			if(y >= ArkanoidMain.HEIGHT - 50) {
				lose = true;
			}
		}
		
		return lose;
		
	}

	public void setDY(double theDY) {
		dy = theDY;
	}
	
	public double getDY() {
		return dy;
	}
	
	public void setDX(double theDX) {
		dx = theDX;	
	}
	
	public double getDX() {
		return dx;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public int getSize() {
		return ballSize;
	}
	
	public void setDamage(int dmg) {
		damageValue = dmg;
	}
	
	public int getDamageValue() {
		return damageValue;
	}
	
	public void setBallPowerLost(boolean v) {
		ballPowerLost = v;
		setPowerLostTimer();
	}
	
	public boolean getBallPowerLost() {
		return ballPowerLost;
	}

	public void setPowerLostTimer() {
		powerLostTimer = System.nanoTime();
	}
	
}
