import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class HUD {

	//Fields
	private int score;
	public static int lifes;
	private Image lifeImg = null; 
	private int level;
	
	//Constructor
	public HUD() {
		
		init();
		
	}
	
	public void init() {
		
		level = 1;
		lifes = 3;
		score = 0;
		lifeImg = getImage("/Images/Lives/life3.png");

	}
	
	public void draw(Graphics2D g) {
		
		drawScore(g);
		
		drawLevel(g);
		
		drawLife(g);
		
	}
	
	public void drawScore(Graphics2D g) {
		g.setFont(new Font("Courier New", Font.PLAIN, 25));
		g.setColor(Color.WHITE);
		g.drawString("Score: " + score, 20, 30);
	}
	
	public void drawLevel(Graphics2D g) {
		g.setFont(new Font("Courier New", Font.PLAIN, 25));
		g.setColor(Color.WHITE);
		g.drawString("Level: " + level, 515, 30);
	}
	
	public void drawLife(Graphics2D g) {
		if(lifes == 3) {
			g.drawImage(lifeImg, 950, 10, 120, 40, null);
		}
		
		else if(lifes == 2) {
			g.drawImage(lifeImg, 980, 10, 80, 40, null);
		}
		
		else if(lifes == 1) {
			g.drawImage(lifeImg, 1010, 10, 40, 40, null);
		}
		
		else {
			g.drawImage(lifeImg, 1010, 10, 40, 40, null);
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
	
	public void setLifeImage(String lifeX) {
		lifeImg = getImage("/Images/Lives/" + lifeX + ".png");
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int scoreToAdd) {
		if(!Ball.ballPowerLost) {
			score += scoreToAdd;
		}
	}
	
	public void setLevel(int x) {
		level = x;
	}
	
	public int getLevel() {
		return level;
	}

}
