import javax.swing.*;
import java.util.*;
import java.awt.*;

public class MainMenuGUI extends JPanel
{
	public JButton newGame, leaderBoard, exit;
	public JLabel title;
	public JPanel mini_menu;

	public MainMenuGUI()
	{
		setLayout(null);
		setBackground(Color.GREEN);

		mini_menu = new JPanel();
		mini_menu.setBounds(75, 150, 250, 225);
		mini_menu.setLayout(new GridLayout(3, 1));
		mini_menu.setBackground(Color.GREEN);

		title = new JLabel("Main Menu");
		title.setBounds(125, 50, 250, 100);
		title.setFont(new Font("Serif", Font.PLAIN, 30));

		newGame = new JButton("New Game");
		leaderBoard = new JButton("Leaderboard");
		exit = new JButton("Exit");

		mini_menu.add(newGame);
		mini_menu.add(leaderBoard);
		mini_menu.add(exit);

		add(mini_menu);
		add(title);
	}
}
