import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndGame {

	public JFrame endGameFrame;
	public JPanel endGamePanel;
	
	public EndGame() {
		
		endGameFrame = new JFrame("Game Over");
		endGameFrame.getContentPane().setBackground(Color.WHITE);
		endGameFrame.setSize(300, 300);
		endGameFrame.setLocationRelativeTo(null);
		endGameFrame.setResizable(false);
		endGameFrame.setVisible(true);
		endGameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	
		endGamePanel = (JPanel) endGameFrame.getContentPane();
		endGamePanel.setLayout(null);
		
		// I should take name from the user for the end game screen. So it can be shown in High Scores.
		// Here, i just try to see some label but it doesn't show itself.
		Font font = new Font("Courier New", Font.ITALIC, 17);
		
		JLabel textLabel = new JLabel("Name");
		textLabel.setFont(font);
		Dimension size = textLabel.getPreferredSize();
		textLabel.setBounds(300 /  2 - size.width / 2, 50, size.width, size.height);
		endGamePanel.add(textLabel);
		endGamePanel.setVisible(true);
			
	}
	
}
