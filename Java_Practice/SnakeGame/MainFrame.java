import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;

public class MainFrame
{
	public JFrame mainFrame = new JFrame("Snake Game");
	public MainMenuGUI menu = new MainMenuGUI();
	public NewGameGUI newGame = new NewGameGUI();
	public SnakeGameGUI snakeGame = new SnakeGameGUI();
	public Player player;
	public Leaderboard lb;
	public Border border = newGame.nametxt.getBorder();
	public int direction;
	public boolean moving;
	public Food food;
	

	public MainFrame()
	{
		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setBounds(0, 0, 400, 500);
		mainFrame.add(menu);
		mainFrame.setVisible(true);
		mainMenuModel();
		newGameModel();
		snakeGameModel(); 
	}

	public void mainMenuModel()
	{
		menu.newGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				mainFrame.remove(menu);
				mainFrame.setBounds(mainFrame.getX(), mainFrame.getY(), 400, 270);
				mainFrame.add(newGame);
				mainFrame.setVisible(true);
			}
		});

		menu.exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		menu.leaderBoard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

			}
		});
	}

	public void newGameModel()
	{
		newGame.back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				mainFrame.remove(newGame);
				mainFrame.setBounds(mainFrame.getX(), mainFrame.getY(), 400, 500);
				mainFrame.add(menu);
				mainFrame.setVisible(true);
				clearNewGameInputs();
			}
		});

		newGame.start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = newGame.nametxt.getText();
				boolean missing = false;

				if(name.equals(""))
				{
					missing = true;
					newGame.nametxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					missing = false;
					newGame.nametxt.setBorder(border);
				}

				if(missing)
				{
					newGame.errorBar.setForeground(Color.RED);
					newGame.errorBar.setText("Error: Missing inputs");
					return;
				}
				else
				{
					player = new Player(newGame.nametxt.getText(), 0, newGame.getDiff());
					lb = new Leaderboard(player.getDiff());
					mainFrame.setVisible(false);
					mainFrame.remove(newGame);
					mainFrame.setBounds(mainFrame.getX(), mainFrame.getY(), 600, 600);
					mainFrame.add(snakeGame);
					mainFrame.setVisible(true);
					if(newGame.getDiff().equals("easy"))
					{
						snakeGame.setSpeed(100);
					}
					else if(newGame.getDiff().equals("med"))
					{
						snakeGame.setSpeed(75);
					}
					else
					{
						snakeGame.setSpeed(50);
					}
				}
			}
		});
	}

	public void snakeGameModel()
	{
		snakeGame.mainMenu.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				mainFrame.setVisible(false);
				mainFrame.remove(snakeGame);
				mainFrame.setBounds(mainFrame.getX(), mainFrame.getY(), 400, 500);
				mainFrame.add(menu);
				mainFrame.setVisible(true);
				newGame.nametxt.setText("");
				clearSnakeGamePanel();
				moving = false;
				snakeGame.restart.setText("Start");
			}
		});

		snakeGame.pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				pauseGame();
			}
		});

		snakeGame.gamePanel.addKeyListener(new KeyListener() 
		{
			public void keyPressed(KeyEvent e)
			{
				int c = e.getKeyCode();
				if(moving == true)
				{
					if(c == 37) // turn to the left
					{
						if(direction != 3)
						{
							direction = 1; 
							System.out.println("left");
						}
					}
					else if(c == 38) //turn upwards
					{
						if(direction != 4)
						{
							direction = 2;
							System.out.println("up");
						}
					}
					else if(c == 39)// turn to the right
					{
						if(direction != 1) 
						{
							direction = 3;
							System.out.println("right"); 
						}
					}
					else if(c == 40) // turn downwards 
					{
						if(direction != 2)
						{
							direction = 4;
							System.out.println("down");
						}
					}
				}
				if(c == KeyEvent.VK_SPACE)
				{
					System.out.println("PAUSE");
					pauseGame();
				}
			}
			public void keyReleased(KeyEvent e)
			{

			}
			public void keyTyped(KeyEvent e) 
			{

			}
		});

		snakeGame.restart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(snakeGame.restart.getText().equals("Restart"))
				{
					snakeGame.removeSnake();
					snakeGame.snake.initializeSnake();
					snakeGame.addSnake();
					snakeGame.gamePanel.remove(food); 
					snakeGame.scoreCounter.setText("Score:   0");
					player.setScore(0);
				}
				snakeGame.restart.setText("Restart");
				start(); 
			}
		}); 
	}

	public void start()
	{
		moving = true;
		direction = 3; 
		addFood();
		move();
	}

	public void pauseGame()
	{
		if(moving == true) 
		{
			moving = false; 
			snakeGame.pause.setText("Resume");
		}
		else
		{
			moving = true;
			snakeGame.pause.setText("Pause");
			move();
		}
	}

	public void move()  
	{
		Thread thread = new Thread(new Runnable() 
		{

			public void run()
			{
				while(moving) 
				{ 
					movement(direction);
					mainFrame.repaint();
					mainFrame.revalidate();
					pause(snakeGame.getSpeed());
				}
			}
		});
		thread.start(); 
	}

	public boolean hasEaten()
	{
		if(snakeGame.snake.body.get(0).getX() == food.getX() && snakeGame.snake.body.get(0).getY() == food.getY())
		{
			player.updateScore(food.getValue());
			snakeGame.scoreCounter.setText("Score:   " + player.getScore());
			return true;
		}
		return false;
	}

	public void movement(int direction) 
	{
		if(direction == 1) //left 
		{
			if(!hasCollided(direction))
			{
				for(int i = snakeGame.snake.body.size() - 1; i > 0; i--) 
				{
					snakeGame.snake.body.get(i).setBounds(snakeGame.snake.body.get(i - 1).getBounds()); 
				}
				snakeGame.snake.body.get(0).setBounds(snakeGame.snake.body.get(0).getX() - 15, snakeGame.snake.body.get(0).getY(), 15 ,15);
			}
		}
		else if(direction == 2) //up 
		{
			if(!hasCollided(direction))
			{
				for(int i = snakeGame.snake.body.size() - 1; i > 0; i--) 
				{
					snakeGame.snake.body.get(i).setBounds(snakeGame.snake.body.get(i - 1).getBounds()); 
				}
				snakeGame.snake.body.get(0).setBounds(snakeGame.snake.body.get(0).getX(), snakeGame.snake.body.get(0).getY() - 15, 15, 15);
			}
		}
		else if(direction == 3) //right
		{
			if(!hasCollided(direction))
			{
				for(int i = snakeGame.snake.body.size() - 1; i > 0; i--) 
				{
					snakeGame.snake.body.get(i).setBounds(snakeGame.snake.body.get(i - 1).getBounds()); 
				}
				snakeGame.snake.body.get(0).setBounds(snakeGame.snake.body.get(0).getX() + 15, snakeGame.snake.body.get(0).getY(), 15 ,15);
			}
		}
		else if(direction == 4) //down 
		{
			if(!hasCollided(direction))
			{
				for(int i = snakeGame.snake.body.size() - 1; i > 0; i--) 
				{
					snakeGame.snake.body.get(i).setBounds(snakeGame.snake.body.get(i - 1).getBounds()); 
				}
				snakeGame.snake.body.get(0).setBounds(snakeGame.snake.body.get(0).getX(), snakeGame.snake.body.get(0).getY() + 15, 15, 15); 
			}
		}
		if(hasEaten())
		{
			snakeGame.gamePanel.remove(food);
			addFood();
			snakeGame.snake.addTail();
			snakeGame.gamePanel.add(snakeGame.snake.body.get(snakeGame.snake.body.size() - 1));
		}
		mainFrame.repaint();
		mainFrame.revalidate(); 
	}

	public void pause(int millis) 
	{
		try
		{
			Thread.sleep(millis);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void clearNewGameInputs()
	{
		newGame.easy.setSelected(true);
		newGame.nametxt.setText("");
		newGame.nametxt.setBorder(border);
		newGame.errorBar.setText("");
	}

	public void clearSnakeGamePanel()
	{
		snakeGame.removeSnake();
		snakeGame.snake.initializeSnake();
		snakeGame.addSnake();
		snakeGame.gamePanel.remove(food); 
		snakeGame.scoreCounter.setText("Score:   0");
		player.setScore(0);
	}

	public boolean hasCollided(int dir)
	{
		for(int i = 1; i < snakeGame.snake.body.size(); i++)
		{
			if(snakeGame.snake.body.get(0).getX() == snakeGame.snake.body.get(i).getX() && snakeGame.snake.body.get(0).getY() == snakeGame.snake.body.get(i).getY())
			{
				moving = false;
				JOptionPane.showMessageDialog(null, "GAME OVER");
				lb.addtoleaderBoard(player);
				return true;
			}
		}
		if(dir == 1)
		{ 
			if(snakeGame.snake.body.get(0).getX() <= 3) 
			{
				moving = false;
				/*for(int i = 0; i < snakeGame.snake.body.size(); i++)
				{
					snakeGame.snake.body.get(i).setVisible(false); 
				}*/ 

				JOptionPane.showMessageDialog(null, "GAME OVER");
				lb.addtoleaderBoard(player);
				return true;
			}
		}
		else if(dir == 2)
		{
			if(snakeGame.snake.body.get(0).getY() <= 3) 
			{
				moving = false;
				for(int i = 0; i < snakeGame.snake.body.size(); i++)
				{
					snakeGame.snake.body.get(i).setVisible(false);
				}
				JOptionPane.showMessageDialog(null, "GAME OVER"); 
				lb.addtoleaderBoard(player);
				return true;
			}
		}
		else if(dir == 3) 
		{
			if(snakeGame.snake.body.get(0).getX() >= snakeGame.gamePanel.getWidth() - 18) 
			{
				moving = false;
				for(int i = 0; i < snakeGame.snake.body.size(); i++)
				{
					snakeGame.snake.body.get(i).setVisible(false);
				}
				JOptionPane.showMessageDialog(null, "GAME OVER");
				lb.addtoleaderBoard(player);
				return true;
			}
		}
		else
		{
			if(snakeGame.snake.body.get(0).getY() >= snakeGame.gamePanel.getHeight() - 18) 
			{
				moving = false;
				for(int i = 0; i < snakeGame.snake.body.size(); i++)
				{
					snakeGame.snake.body.get(i).setVisible(false);
				}
				JOptionPane.showMessageDialog(null, "GAME OVER");
				lb.addtoleaderBoard(player);
				return true;
			}
		}
		return false;
	}

	public void addFood()
	{
		Random rand = new Random();
		while(true)
		{
			int x = ((rand.nextInt(snakeGame.gamePanel.getWidth()/15)) * 15) + 3;
			int y = ((rand.nextInt(snakeGame.gamePanel.getHeight()/15)) * 15) + 3;
			if(canPlace(x, y))
			{
				food = new Food(x, y);
				snakeGame.gamePanel.add(food);
				break;
			}
		}
	}

	public boolean canPlace(int x, int y)
	{
		for(int i = 0; i < snakeGame.snake.body.size(); i++)
		{
			if(snakeGame.snake.body.get(i).getX() == x && snakeGame.snake.body.get(i).getY() == y)
			{
				return false;
			}
		}
		return true;
	}


}