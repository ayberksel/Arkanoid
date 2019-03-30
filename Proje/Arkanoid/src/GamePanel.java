import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	//Fields
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MyKeyListener theKeyListener;    
	private Image backgroundImage = null;
	
	//Entities
	private Ball theBall;
	private Paddle thePaddle;
	private Level1 theMap;
	// Below map's are to see the every level individually.
	//private Level3 theMap;
	//private Level2 theMap;
	//private Level4 theMap;
	private HUD theHud;
	private ArrayList<PowerUp> powerUps;
	
	//Constructor
	public GamePanel() {

		backgroundImage = getImage("/Images/Backgrounds/background.jpg");

		init();

	}

	public void init() {
				
		theBall 	= new Ball();
		thePaddle 	= new Paddle();
		theMap		= new Level1(4, 10);
		// Below map's are to see the every level individually.
		//theMap		= new Level4(4, 10);
		//theMap		= new Level3(5, 12);
		//theMap		= new Level2(6, 10);
		theHud 		= new HUD();
		powerUps = new ArrayList<PowerUp>();
		
		this.setFocusable(true);
		
		theKeyListener = new MyKeyListener();
		addKeyListener(theKeyListener);
		
		running = true;
		image = new BufferedImage(ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	}
	
	public void playGame() {
		//Game Loop
		//Pause for 2 seconds at the start of the game, so the user can see the ball and the paddle position.
		pause(2000);
		
		while(running) {
			//update
			update();
			
			//draw
			draw();
			
			//display
			repaint();
			
			try {
				Thread.sleep(10);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void pause(int x) {
		//Pause for x ms before start
		try {
			Thread.sleep(x);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void update() {
			
		checkCollisions();
		
		theBall.update();
		
		thePaddle.update();
		
		for(PowerUp pu : powerUps) {
			pu.update();
		}	
		
	}
	
	public void draw() {
		//Draw background
		g.drawImage(backgroundImage, 0, 0, ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, null);

		//Draw entities
		theBall.draw(g);
		thePaddle.draw(g);
		theMap.draw(g);
		theHud.draw(g);
		drawPowerUps();
		
		if(theMap.win()) {
			playSound("file:./Resources/gameWin.aiff", 0);
			
			if(theHud.getLevel() == 1) {
				pause(3000);
				drawNextLevel(); //isn't working
				theMap = theMap.isCompleted();
				theBall.init();
				theHud.setLevel(2);
				thePaddle.init();
				playGame();
			}
			else if(theHud.getLevel() == 2) {
				pause(3000);
				drawNextLevel(); //isn't working
				theMap = theMap.isCompleted();
				theBall.init();
				theHud.setLevel(3);
				thePaddle.init();
				playGame();
			}
			else if(theHud.getLevel() == 3) {
				pause(3000);
				drawNextLevel(); //isn't working
				theMap = theMap.isCompleted();
				theBall.init();
				theHud.setLevel(4);
				thePaddle.init();
				playGame();
			}
			else {
				drawWin();
				running = false;
			}
		}
		
		if(theBall.lose()) {
			playSound("file:./Resources/gameOver.aiff", 0);
			HUD.lifes--;
			if(HUD.lifes != 0) {				
				theHud.setLifeImage("life" + HUD.lifes);
				pause(1000);
				theBall.init();
				thePaddle.init();
			}
			else {
				theHud.setLifeImage("life0");
				pause(2500);
				drawLose(); //isn't working
				///
				
				NewGame.gameFrame.setVisible(false);
				
				EndGame eg = new EndGame(); //Game Over frame is not working as intended.
							
				ArkanoidMain.theFrame.setVisible(true);
				
				///
				running = false;
			}
		}	
		
	}
	
	public void drawPowerUps() {
		for(PowerUp pu : powerUps) {
			pu.draw(g);
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
	
	public void drawNextLevel() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 50));
		g.drawString("Next Level in 3..2.1!",  (ArkanoidMain.WIDTH - 150) / 2, (ArkanoidMain.HEIGHT / 2) - 50);
	}
	
	public void drawWin() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 50));
		g.drawString("Winner!",  (ArkanoidMain.WIDTH - 150) / 2, (ArkanoidMain.HEIGHT / 2) - 50);
	}
	
	public void drawLose() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 50));
		g.drawString("Loser!",  (ArkanoidMain.WIDTH - 150) / 2, (ArkanoidMain.HEIGHT / 2) - 50);
	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(image, 0, 0, ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, null);

		g2.dispose();

	}
	
	public void checkCollisions() {
		
		Rectangle ballRect = theBall.getRect();
		Rectangle paddleRect = thePaddle.getRect();
		Rectangle shieldRect = thePaddle.getShieldRect();
		
		paddlePowerUpCollision(paddleRect);
		
		if(ballRect.intersects(paddleRect) || (ballRect.intersects(shieldRect) && Paddle.shieldActive)) {
			
			theBall.setY(thePaddle.YPOS - theBall.getSize());
			
			theBall.setDY(-theBall.getDY());
			
			playSound("file:./Resources/paddleHit.aiff", 0);
			
			//Ball is left side of the paddle
			if(theBall.getX() + theBall.getSize() < thePaddle.getX() + thePaddle.getWidth() / 3) {
				theBall.setDX(theBall.getDX() - 0.5);
			}
			
			//Ball is right side of the paddle
			if(theBall.getX() < thePaddle.getX() + thePaddle.getWidth() && theBall.getX() >= thePaddle.getX() + thePaddle.getWidth() / 3) {
				theBall.setDX(theBall.getDX() + 0.5);
			}
			
		}
				
		ballBrickCollision(ballRect);
		
	}
	
	public void paddlePowerUpCollision(Rectangle paddleRect) {
		for(int i = 0; i < powerUps.size(); i++) {
			
			Rectangle puRect = powerUps.get(i).getRect();
			
			if(paddleRect.intersects(puRect)) {
						
				if(!powerUps.get(i).getWasUsed()) {
					
					playSound("file:./Resources/powerup.aiff", 0);

					if(powerUps.get(i).getType() == PowerUp.WIDEPADDLE) {
						thePaddle.setWidth(thePaddle.getWidth() * 11/10);
						powerUps.get(i).setWasUsed(true);
					}
					
					if(powerUps.get(i).getType() == PowerUp.SHRINKPADDLE) {
						thePaddle.setWidth(thePaddle.getWidth() - 20);
						powerUps.get(i).setWasUsed(true);
					}
					
					if(powerUps.get(i).getType() == PowerUp.BALLSPEEDINC) {
						theBall.setDX(theBall.getDX() * 3/2);
						theBall.setDY(theBall.getDY() * 3/2);
						powerUps.get(i).setWasUsed(true);
					}
					
					if(powerUps.get(i).getType() == PowerUp.BALLSPEEDDEC) {
						theBall.setDX(theBall.getDX() * 4/5);
						theBall.setDY(theBall.getDY() * 4/5);
						powerUps.get(i).setWasUsed(true);
					}
					
					// Ball becomes unstable when collisions happens with the bricks. So it is commented.
					/*if(powerUps.get(i).getType() == PowerUp.BALLPOWERLOSE) {
						theBall.setDamage(0);
						theBall.setBallPowerLost(true);
						powerUps.get(i).setWasUsed(true);
					}*/
					
					if(powerUps.get(i).getType() == PowerUp.EXTRALIFE) {
						if(HUD.lifes < 3) {
							playSound("file:./Resources/gainLife.aiff" ,0);
							HUD.lifes++;
							theHud.setLifeImage("life" + HUD.lifes);
						}
						powerUps.get(i).setWasUsed(true);
					}
					
					if(powerUps.get(i).getType() == PowerUp.LOSELIFE) {
						if(HUD.lifes > 0) {
							playSound("file:./Resources/gameOver.aiff" ,0);
							HUD.lifes--;
							if(HUD.lifes != 0) {
								theHud.setLifeImage("life" + HUD.lifes);
							}
							else {
								theHud.setLifeImage("life" + HUD.lifes);
								drawLose(); //its not working
								running = false;
							}
						}
						powerUps.get(i).setWasUsed(true);
					}
					
					if(powerUps.get(i).getType() == PowerUp.SHIELD) {
						thePaddle.setShieldActive(true);
						powerUps.get(i).setWasUsed(true);
					}
				}
			}
		}
	}
	
	public void ballBrickCollision(Rectangle ballRect) {
		A: for(int row = 0; row < theMap.getMapArray().length; row++) {
			for(int col = 0; col < theMap.getMapArray()[0].length; col++) {
				if(theMap.getMapArray()[row][col] > -1) {
					int brickx		= col * theMap.getBrickWidth() + theMap.HOR_PAD;
					int bricky		= row * theMap.getBrickHeight() + theMap.VERT_PAD;
					int brickWidth	= theMap.getBrickWidth();
					int brickHeight	= theMap.getBrickHeight();
					
					Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);
					
					if(ballRect.intersects(brickRect)) {
						
						// If i add : && theMap.getMapArray()[row][col] == 1 : to the below if,
						// same brick will stop to drop 2 power ups. (2-hit bricks)
						if((theMap.getMapArray()[row][col] != 0)) {

							playSound("file:./Resources/brickHit.aiff", 0);

							if((theMap.getBrickArray()[row][col] == PowerUp.WIDEPADDLE)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.WIDEPADDLE, 40, 40));
							}
							
							if((theMap.getBrickArray()[row][col] == PowerUp.SHRINKPADDLE)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.SHRINKPADDLE, 40, 40));
							}
							
							if((theMap.getBrickArray()[row][col] == PowerUp.BALLSPEEDINC)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.BALLSPEEDINC, 40, 40));
							}
							
							if((theMap.getBrickArray()[row][col] == PowerUp.BALLSPEEDDEC)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.BALLSPEEDDEC, 40, 40));
							}
							
							/*if((theMap.getBrickArray()[row][col] == PowerUp.BALLPOWERLOSE)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.BALLPOWERLOSE, 40, 40));
							}*/
							
							if((theMap.getBrickArray()[row][col] == PowerUp.EXTRALIFE)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.EXTRALIFE, 40, 40));
							}
							
							if((theMap.getBrickArray()[row][col] == PowerUp.LOSELIFE)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.LOSELIFE, 40, 40));
							}
							
							if((theMap.getBrickArray()[row][col] == PowerUp.SHIELD)) {
								powerUps.add(new PowerUp(brickx, bricky, PowerUp.SHIELD, 40, 40));
							}
							
						}
						
						if(theMap.getMapArray()[row][col] != 0 /*&& !Ball.ballPowerLost*/) {
							theHud.addScore(50);
						}
						
						//if(!Ball.ballPowerLost) {
							theMap.hitBrick(row, col);
						//}
					
						theBall.setDY(-theBall.getDY());
						
						break A;
					}	
				}
			}
		}
	}
	
	public void playSound(String soundFile, int times) {
		
		try {
			
			URL soundLocation = new URL(soundFile);
			AudioInputStream audio = AudioSystem.getAudioInputStream(soundLocation);
			Clip clip = AudioSystem.getClip();
			clip.open(audio);
			clip.loop(times);
			clip.start();
			
		} catch(UnsupportedAudioFileException uae) {
			System.out.println(uae);
		} catch(IOException ioe) {
			System.out.println(ioe);
		} catch(LineUnavailableException lua) {
			System.out.println(lua);
		}
		
	}
	
	private class MyKeyListener implements KeyListener {
		
		private boolean right, left;
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					right = true;
					thePaddle.move(right, left);
					break;
				case KeyEvent.VK_LEFT:
					left = true;
					thePaddle.move(right, left);
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch(e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					right = false;
					thePaddle.move(right, left);
					break;
				case KeyEvent.VK_LEFT:
					left = false;
					thePaddle.move(right, left);
					break;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		playGame();
	}
	
}