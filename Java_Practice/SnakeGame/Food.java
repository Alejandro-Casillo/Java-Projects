import javax.swing.*;
import java.awt.*;

public class Food extends JPanel
{
	public int value = 5;

	public Food(int x, int y) 
	{
		setBounds(x, y, 15, 15);
		setBackground(Color.RED);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	}

	public int getValue()
	{
		return value; 
	}
}