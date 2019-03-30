import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Level2 extends Level1 {
	//Fields
	private int[][] theMap;
	private int brickHeight, brickWidth;
	private int bricksRemaining;
	private int[][] brickArray;
	
	public final int HOR_PAD = 150, VERT_PAD = 100;
	
	//Constructor
	public Level2(int row, int col) {
		
		initMap(row, col);
		
		brickWidth 	= (ArkanoidMain.WIDTH - 2 * HOR_PAD) / col;
		brickHeight	= (ArkanoidMain.HEIGHT / 2 - VERT_PAD * 2) / row; 
		
	}
	
	public void initMap(int row, int col) {
		
		bricksRemaining = 0;
		
		theMap = new int[row][col];
		brickArray = new int[row][col];
		
		int value = 2;
		for(int i = 0; i < theMap.length; i++) {
			for(int j = 0; j < theMap[0].length; j++) {
				if(value == 0) {
					value = 2;
				}
				theMap[i][j] = value;
				bricksRemaining += value;
				Brick brick = new Brick();
				brickArray[i][j] = brick.getPowerUpVal();
				value--;
			}
		}
	}
	
	public void draw(Graphics2D g) {
		
		for(int row = 0; row < theMap.length; row++) {
			for(int col = 0; col < theMap[0].length; col++) {
				
				if(theMap[row][col] > -1) {
					
					//Draw the colors for the values of breakable bricks ( 1 or 2 hit )
					if(theMap[row][col] == 1) {
						g.setColor(new Color(255,99,71));
					}
					if(theMap[row][col] == 2) {
						g.setColor(new Color(128, 0, 0));
					}
			
					
					g.fillRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
				
				}	
			}	
		}		
	}
	
	public boolean win() {
		
		boolean win = false;
		
		if(bricksRemaining == 0) {
			win = true;
		}
		
		return win;
		
	}
	
	public int[][] getMapArray(){
		return theMap;
	}
	
	public void setBrick(int row, int col, int value) {
		theMap[row][col] = value;
	}
	
	public int getBrickWidth() {
		return brickWidth;
	}
	
	public int getBrickHeight() {
		return brickHeight;
	}
	
	public void hitBrick(int row, int col) {
				
		if(theMap[row][col] != 0) {
			
			theMap[row][col] -= 1;
			bricksRemaining --;
			
			if(theMap[row][col] == 0) {
				theMap[row][col] = -1;
			}
		}
	}

	public int[][] getBrickArray() {
		return brickArray;
	}

	public void setBrickArray(int[][] brickArray) {
		this.brickArray = brickArray;
	}
	
	public Level1 isCompleted() {
		Level3 theMap	= new Level3(5, 12);
		return theMap;
	}
}