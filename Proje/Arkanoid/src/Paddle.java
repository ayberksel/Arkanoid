import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class Paddle {

	//Fields
	private double x;
	private int width, height;
	private long shieldTimer;
	private Image paddleImg = null; 
	private Image shieldImg = null;
	public static boolean shieldActive;
	
	public final int YPOS = ArkanoidMain.HEIGHT - 100;
	public int dp = 35;	//Speed of the paddle
	
	//Constructor
	public Paddle() {
		
		init();
		
	}
	
	public void init() {
		
		paddleImg = getImage("/Images/Paddles/paddle1.png");
		shieldImg = getImage("/Images/shield1.png");
		
		shieldActive = false;
		
		if(MenuPanel1.difficulty == "Novice") {
			width 	= 170;
			height 	= 50;
		}
		
		else if(MenuPanel1.difficulty == "Intermediate") {
			width 	= 135;
			height 	= 50;
		}
		
		else if(MenuPanel1.difficulty == "Advanced") {
			width 	= 100;
			height 	= 50;
		}
		
		x 		= ArkanoidMain.WIDTH / 2 - width / 2;
	}
	
	public void update() {
		
		if((System.nanoTime() - shieldTimer) / 1000 > 15000000) {
			shieldActive = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(paddleImg, (int)x, YPOS, width, height, null);

		if(shieldActive) {
			g.drawImage(shieldImg, 0, YPOS, 1080, height, null);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.PLAIN, 25));
			g.drawString("Shield: " + (15 - ((System.nanoTime() - shieldTimer) / 1000000000)), 750, 30);
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
	
	public void move(boolean right, boolean left) {
		
		if(right) {
			x += dp;
		}
		if(left) {
			x -= dp;
		}
		
		if(x <= 0) {
			x = 0;
		}
		
		if(x >= ArkanoidMain.WIDTH - width) {
			x = ArkanoidMain.WIDTH - width;
		}
	}
	
	
	public Rectangle getRect() {
		return new Rectangle((int)x, YPOS, width, height);
	}
	
	public Rectangle getShieldRect() {
		return new Rectangle(0, YPOS, 1080, height);
	}
	
	public void setWidth(int newWidth) {
		if(!(width<50)) {
			width = newWidth;
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public double getX() {
		return x;
	}
	
	public void setShieldActive(boolean v) {
		shieldActive = v;
		setShieldTimer();
	}
	
	public boolean getShieldActive() {
		return shieldActive;
	}

	public void setShieldTimer() {
		shieldTimer = System.nanoTime();
	}
}
