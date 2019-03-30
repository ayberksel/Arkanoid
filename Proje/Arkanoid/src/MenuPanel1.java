import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MenuPanel1 extends JPanel{
	
	//Fields
	private MouseListener theMouseListener;
	
	private boolean inGame;
	
	private Graphics2D g;
	private BufferedImage image;
	
	private Image backgroundImg = null;
	private Image newGameImg = null;
	private Image optionsImg = null;
	private Image highScoresImg = null;
	private Image aboutImg = null;
	private Image helpImg = null;
	private Image exitImg = null;

	private Rectangle newGameRect;
	private Rectangle optionsRect;
	private Rectangle highScoreRect;
	private Rectangle aboutRect;
	private Rectangle helpRect;
	private Rectangle exitRect;
	
	private JLabel optionsStr;
	
	public static String difficulty; 
	
	//Constructor
	public MenuPanel1(){
	
		inGame = false;
		
		init();		
	
	}
	
	public void init() {
	
		backgroundImg = getImage("/Images/Backgrounds/background2.jpg");
		newGameImg = getImage("/Images/MenuButtons/NEW_GAME.png");
		optionsImg = getImage("/Images/MenuButtons/OPTIONS.png");
		highScoresImg = getImage("/Images/MenuButtons/HIGH_SCORES.png");
		aboutImg = getImage("/Images/MenuButtons/ABOUT.png");
		helpImg = getImage("/Images/MenuButtons/HELP.png");
		exitImg = getImage("/Images/MenuButtons/EXIT.png");
		
		newGameRect = getRect(100);
		optionsRect = getRect(170);
		highScoreRect = getRect(240);
		aboutRect = getRect(310);
		helpRect = getRect(380);
		exitRect = getRect(450);

		difficulty =  "Novice";
		
		optionsStr = new JLabel("Game difficulty set to " + difficulty + " by default");
		
		this.setFocusable(true);

		theMouseListener = new myMouseListener();
		addMouseListener(theMouseListener);
		
		image = new BufferedImage(ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	
	public void showMenu() {
		
		while(!inGame) {
			draw();
			repaint();
			try {
				Thread.sleep(10);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void draw() {
		g.drawImage(backgroundImg, 0, 0, ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, null);
		g.drawImage(newGameImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 100, 250, 50, null);
		g.drawImage(optionsImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 170, 250, 50, null);
		g.drawImage(highScoresImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 240, 250, 50, null);
		g.drawImage(aboutImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 310, 250, 50, null);
		g.drawImage(helpImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 380, 250, 50, null);
		g.drawImage(exitImg, ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, 450, 250, 50, null);

	}
	
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;

		g2.drawImage(image, 0, 0, ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT, null);

		g2.dispose();

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
	
	public Rectangle getRect(int h) {
		return new Rectangle(ArkanoidMain.WIDTH / 2 - newGameImg.getWidth(null) / 2, h, 250, 50);
	}
	
	private class myMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			if(newGameRect.contains(e.getX(), e.getY())) {
				System.out.println("New Game pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);
				
				NewGame ng = new NewGame();
				
				Thread gp = new Thread(ng.gamePanel);
				gp.start();
								
				inGame = true;

			}
			
			else if(optionsRect.contains(e.getX(), e.getY())) {
				System.out.println("Options pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);

				JFrame optionsFrame = new JFrame("Options");
				optionsFrame.getContentPane().setBackground(Color.WHITE);
				optionsFrame.setSize(300, 300);
				optionsFrame.setLocationRelativeTo(null);
				optionsFrame.setResizable(false);
				optionsFrame.setVisible(true);
				optionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JPanel optionsPanel = (JPanel) optionsFrame.getContentPane();
				optionsPanel.setLayout(null);
				
				Font font = new Font("Helvetica Neue", Font.BOLD, 18);
				Font infoFont = new Font("Helvetica Neue", Font.PLAIN, 10);
			     
				
				JButton novice = new JButton("Novice");
				JButton intermediate = new JButton("Intermediate");
				JButton advanced = new JButton("Advanced");
								
				novice.setFont(font);
				intermediate.setFont(font);
				advanced.setFont(font);
				optionsStr.setFont(infoFont);
				
				Dimension size = novice.getPreferredSize();
				Dimension size1 = intermediate.getPreferredSize();
				Dimension size2 = advanced.getPreferredSize();
				Dimension size3 = optionsStr.getPreferredSize();

				novice.setBounds(300 /  2 - size.width / 2, 40, size.width, size.height);
				intermediate.setBounds(300 /  2 - size1.width / 2, 100, size1.width, size1.height);
				advanced.setBounds(300 /  2 - size2.width / 2, 160, size2.width, size2.height);
				optionsStr.setBounds(300 /  2 - size3.width / 2, 220, size3.width, size3.height);

				optionsPanel.add(novice);
				optionsPanel.add(intermediate);
				optionsPanel.add(advanced);
				optionsPanel.add(optionsStr);

				novice.addActionListener(new ActionListener() {
					  @Override
					    public void actionPerformed(ActionEvent e)
					    {
							playSound("file:./Resources/buttonClick.aiff", 0);

						  	difficulty = "Novice";
						  	optionsStr.setText("Game difficulty set to " + difficulty);
					        Dimension size = optionsStr.getPreferredSize();
					        optionsStr.setBounds(300 /  2 - size.width / 2, 220, size.width, size.height);
					    }
				});
				
				intermediate.addActionListener(new ActionListener() {
					  @Override
					    public void actionPerformed(ActionEvent e)
					    {
							playSound("file:./Resources/buttonClick.aiff", 0);

						  	difficulty = "Intermediate";
						  	optionsStr.setText("Game difficulty set to " + difficulty);
					        Dimension size = optionsStr.getPreferredSize();
					        optionsStr.setBounds(300 /  2 - size.width / 2, 220, size.width, size.height);
					    }
				});
				
				advanced.addActionListener(new ActionListener() {
					  @Override
					    public void actionPerformed(ActionEvent e)
					    {
							playSound("file:./Resources/buttonClick.aiff", 0);

						  	difficulty = "Advanced";
						  	optionsStr.setText("Game difficulty set to " + difficulty);
					        Dimension size = optionsStr.getPreferredSize();
					        optionsStr.setBounds(300 /  2 - size.width / 2, 220, size.width, size.height);
					    }
				});
				
				inGame = false;				
				
			}
			
			else if(highScoreRect.contains(e.getX(), e.getY())) {
				System.out.println("High Scores pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);
				
				JFrame highScoreFrame = new JFrame("High Scores");
				highScoreFrame.getContentPane().setBackground(Color.WHITE);
				highScoreFrame.setSize(500, 620);
				highScoreFrame.setLocationRelativeTo(null);
				highScoreFrame.setResizable(false);
				highScoreFrame.setVisible(true);
				highScoreFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JPanel scorePanel = (JPanel) highScoreFrame.getContentPane();
				scorePanel.setLayout(null);
				
		        HighscoreManager hm = new HighscoreManager();
		        hm.addScore("Bart",240);
		        hm.addScore("Marge",300);
		        hm.addScore("Maggie",220);
		        hm.addScore("Homer",100);
		        hm.addScore("Lisa",270);

		        ArrayList<Score> hsArray =hm.getScoresArray();
		        JLabel hs1 = new JLabel();
		        int h = 40;
	        	Font font = new Font("Helvetica Neue", Font.BOLD, 15);
	        	Collections.sort(hsArray);
		        for(int i = 0; i <= 4; i++) {

				    hs1.setText(i+1 + ".     " + hsArray.get(i).getName() + "     " + String.valueOf(hsArray.get(i).getScore()));
		        	hs1.setFont(font);
					
					Dimension size = hs1.getPreferredSize();
					
					hs1.setBounds(500 /  2 - size.width / 2, h, size.width, size.height);
					
					System.out.println(hs1.getText() + "\tHeight at: " + h);

					scorePanel.add(hs1);
					h += 40;
		        }
				
		        inGame = false;
			}
			
			else if(helpRect.contains(e.getX(), e.getY())) {
				System.out.println("Help pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);
				
				JFrame helpFrame = new JFrame("Help");
				helpFrame.getContentPane().setBackground(Color.WHITE);
				helpFrame.setSize(500, 500);
				helpFrame.setLocationRelativeTo(null);
				helpFrame.setResizable(false);
				helpFrame.setVisible(true);
				helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				JPanel helpPanel = (JPanel) helpFrame.getContentPane();
				helpPanel.setLayout(null);
												
				JTextArea textLabel = new JTextArea("  Players should be trying to break all bricks on every level to\r\nget " + 
						"scores as high as possible and advance to the next level.", 2, 80);
				
				JTextArea textLabel1 = new JTextArea("  Players should slide the paddle left and right by using left\r\n" +
				"and right arrow keys on the keyboard to bounce back the ball.", 2, 80);
				
				JTextArea textLabel2 = new JTextArea("  A game ends when the ball touches the bottom of the screen when\r\nthe player has no more lives left." , 3, 80);
		
				setTextArea(textLabel);
				setTextArea(textLabel1);
				setTextArea(textLabel2);

				Dimension size = textLabel.getPreferredSize();
				Dimension size1 = textLabel1.getPreferredSize();
				Dimension size2 = textLabel2.getPreferredSize();

				textLabel.setBounds(600 /  2 - size.width / 2, 50, size.width, size.height);
				textLabel1.setBounds(600 /  2 - size1.width / 2, 100, size1.width, size1.height);
				textLabel2.setBounds(600 /  2 - size2.width / 2, 150, size2.width, size2.height);
				
				helpPanel.add(textLabel);
				helpPanel.add(textLabel1);
				helpPanel.add(textLabel2);

				inGame = false;
				
			}
			
			
			else if(aboutRect.contains(e.getX(), e.getY())) {
				System.out.println("About pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);
				
				JFrame aboutFrame = new JFrame("About");
				aboutFrame.getContentPane().setBackground(Color.WHITE);
				aboutFrame.setSize(300, 300);
				aboutFrame.setLocationRelativeTo(null);
				aboutFrame.setResizable(false);
				aboutFrame.setVisible(true);
				aboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				JPanel aboutPanel = (JPanel) aboutFrame.getContentPane();
				aboutPanel.setLayout(null);
				
				Font font = new Font("Courier New", Font.ITALIC, 17);
				
				JLabel textLabel = new JLabel("Ayberk Seller");
				JLabel textLabel1 = new JLabel("20150702066");
				JLabel textLabel2 = new JLabel("ayberksel@hotmail.com");
				
				textLabel.setFont(font);
				textLabel1.setFont(font);
				textLabel2.setFont(font);

				Dimension size = textLabel.getPreferredSize();
				Dimension size1 = textLabel1.getPreferredSize();
				Dimension size2 = textLabel2.getPreferredSize();

				textLabel.setBounds(300 /  2 - size.width / 2, 50, size.width, size.height);
				textLabel1.setBounds(300 /  2 - size1.width / 2, 110, size1.width, size1.height);
				textLabel2.setBounds(300 /  2 - size2.width / 2, 170, size2.width, size2.height);

				aboutPanel.add(textLabel);
				aboutPanel.add(textLabel1);
				aboutPanel.add(textLabel2);

				inGame = false;
				
			}
			
			else if(exitRect.contains(e.getX(), e.getY())) {
				System.out.println("Exit pressed.");
				playSound("file:./Resources/buttonClick.aiff", 0);
				
				System.exit(0);
				
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void setTextArea(JTextArea textLabel) {

		Font font = new Font("Courier New", Font.PLAIN, 12);
		textLabel.setFont(font);
		textLabel.setLineWrap(true);
		textLabel.setWrapStyleWord(true);
		textLabel.setOpaque(false);
		textLabel.setEditable(false);
		
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
	
	
}
