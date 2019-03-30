import javax.swing.JFrame;

public class ArkanoidMain {
	
	public static final int WIDTH = 1080, HEIGHT = 720; 
	
	public static JFrame theFrame = new JFrame("Main Menu");
	
	public static MenuPanel1 menuPanel = new MenuPanel1();
	
	public static void main(String[] args) {
		
		theFrame = new JFrame("Main Menu");
		
		theFrame.setSize(WIDTH, HEIGHT);
		theFrame.setLocationRelativeTo(null);
		theFrame.setResizable(false);
		theFrame.setVisible(true);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		theFrame.add(menuPanel);
		
		menuPanel.showMenu();
		
	}
}