import javax.swing.*;
import java.awt.*;

public class SnakeGameGUI extends JPanel 
{
	public JPanel gamePanel, food;
	public JLabel title, scoreCounter;
	public JButton restart, pause, mainMenu;
	public int speed;
	public SnakeGUI snake = new SnakeGUI(); 

	public SnakeGameGUI()
	{
		setLayout(null);
		//setBackground(Color.GREEN);

		gamePanel = new JPanel();
		gamePanel.setLayout(null);
		gamePanel.setBounds(177, 147, 411, 411);
		gamePanel.setFocusable(true);
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		title = new JLabel("Snake Game");
		title.setBounds(230, 70, 170, 60);
		title.setFont(new Font("Serif", Font.PLAIN, 30));

		restart = new JButton("Start");
		restart.setFocusable(false);
		restart.setBounds(45, 150, 100, 50);

		pause = new JButton("Pause");
		pause.setFocusable(false);
		pause.setBounds(45, 220, 100, 50);

		mainMenu = new JButton("Main Menu");
		mainMenu.setFocusable(false);
		mainMenu.setBounds(45, 290, 100, 50);

		scoreCounter = new JLabel("Score:   " + 0);
		scoreCounter.setBounds(60, 360, 100, 30);

		addSnake(); 

		add(restart);
		add(title);
		add(gamePanel);
		add(mainMenu);
		add(scoreCounter);
		add(pause);

		gamePanel.requestFocus();
		gamePanel.requestFocusInWindow();
	}

	public void addSnake()
	{
		for(int i = 0; i < snake.body.size(); i++)
		{
			gamePanel.add(snake.body.get(i)); 
		}
	}

	public void removeSnake()
	{
		for(int i = 0; i < snake.body.size(); i++)
		{
			gamePanel.remove(snake.body.get(i)); 
		}
	}

	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public int getSpeed()
	{
		return speed;
	}
}

