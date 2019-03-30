import javax.swing.JFrame;

public class NewGame {

	public static JFrame gameFrame;
	public GamePanel gamePanel;
	
	public NewGame() {

		gameFrame = new JFrame("Arkanoid");
		gameFrame.setSize(ArkanoidMain.WIDTH, ArkanoidMain.HEIGHT);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ArkanoidMain.theFrame.setVisible(false);

		gamePanel = new GamePanel();
		gameFrame.getContentPane().add(gamePanel);
		gamePanel.setVisible(true);
	
	}

}
