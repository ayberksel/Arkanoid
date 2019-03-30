import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.net.URL;

public class PowerUp {

	//Fields
	private int x, y, dy,type, width, height;
	private boolean isOnScreen;
	private boolean wasUsed;
	private Color color;
	private Image powerUpImg = null; 

	public final static int WIDEPADDLE		= 1;
	public final static int SHRINKPADDLE	= 2;
	public final static int BALLSPEEDINC	= 3;
	public final static int BALLSPEEDDEC	= 4;
	public final static int FIREBALL		= 5;
	public final static int BALLPOWERLOSE	= 6;
	public final static int EXTRALIFE		= 7;
	public final static int LOSELIFE		= 8;
	public final static int CATCHBALL		= 9;
	public final static int SHIELD			= 10;
	public final static int SHOOTLASER		= 11;
	
	
	//Constructor
	public PowerUp(int xStart, int yStart, int theType, int theWidth, int theHeight) {
	
		x		= xStart;
		y		= yStart;
		type	= theType;
		width	= theWidth;
		height	= theHeight;
		
		loadPowerUpImg(type);
		
		//Random speed of power up's gravity
		dy = (int) (Math.random() * 6 + 1);
		
		setWasUsed(false);
		
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(powerUpImg, x, y, width, height, null);
		
	}
	
	public void update() {
		
		y += dy;
		
		// Disappear the power up once it is below the paddle
		if(y > ArkanoidMain.HEIGHT - 120) {
			powerUpImg = getImage("/Images/PowerUps/EMPTY.png");
			isOnScreen = false;
		}
	
	}

	public void loadPowerUpImg(int type) {
		if(type == WIDEPADDLE) {
			powerUpImg = getImage("/Images/PowerUps/WIDEPADDLE.png");
		}
		
		if(type == SHRINKPADDLE) {
			powerUpImg = getImage("/Images/PowerUps/SHRINKPADDLE.png");
		}
		
		if(type == BALLSPEEDINC) {
			powerUpImg = getImage("/Images/PowerUps/BALLSPEEDINC.png");
		}
		
		if(type == BALLSPEEDDEC) {
			powerUpImg = getImage("/Images/PowerUps/BALLSPEEDDEC.png");
		}
		
		if(type == FIREBALL) {
			powerUpImg = getImage("/Images/PowerUps/FIREBALL.png");
		}
		
		if(type == BALLPOWERLOSE) {
			powerUpImg = getImage("/Images/PowerUps/BALLPOWERLOSE.png");
		}
		
		if(type == EXTRALIFE) {
			powerUpImg = getImage("/Images/PowerUps/EXTRALIFE.png");
		}
		
		if(type == LOSELIFE) {
			powerUpImg = getImage("/Images/PowerUps/LOSELIFE.png");
		}
		
		if(type == CATCHBALL) {
			powerUpImg = getImage("/Images/PowerUps/CATCHBALL.png");
		}
		
		if(type == SHIELD) {
			powerUpImg = getImage("/Images/PowerUps/SHIELD.png");
		}
		
		if(type == SHOOTLASER) {
			powerUpImg = getImage("/Images/PowerUps/SHOOTLASER.png");
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isOnScreen() {
		return isOnScreen;
	}

	public void setOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
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

	public boolean getWasUsed() {
		return wasUsed;
	}

	public void setWasUsed(boolean wasUsed) {
		this.wasUsed = wasUsed;
	}
	
}
