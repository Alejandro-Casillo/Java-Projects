import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.border.*;

public class SnakeGUI 
{
	public ArrayList<JPanel> body = new ArrayList<>(); 

	public SnakeGUI()
	{
		initializeSnake();
	}

	public void addTail()
	{
		JPanel square = new JPanel();
		square.setBackground(Color.GREEN);
		square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		body.add(square);
	}

	public void initializeSnake() 
	{
		body.clear();

		for(int i = 0; i < 2; i++) 
		{
			JPanel square = new JPanel();
			square.setBounds(15 - (15*i) + 3, 3, 15, 15);
			square.setBackground(Color.GREEN);
			square.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			body.add(square); 
		}
	}
}