import javax.swing.*;
import java.awt.*;

public class NewGameGUI extends JPanel
{
	public JLabel newGameTitle, name, errorBar;
	public JButton start, back;
	public JTextField nametxt;
	public JRadioButton easy, med, hard;
	public ButtonGroup diffGroup;
	public JPanel diffPanel;

	public NewGameGUI()
	{
		setLayout(null);

		//setBounds(0, 0, 400, 250);
		//setBackground(Color.RED);

		diffPanel = new JPanel();
		diffPanel.setBounds(50, 130, 270, 50);
		diffPanel.setLayout(new GridLayout(1, 3));

		initializeRadioButtons(diffPanel);

		newGameTitle = new JLabel("New Game");
		newGameTitle.setBounds(130, 20, 150, 50);
		newGameTitle.setFont(new Font("Serif", Font.PLAIN, 24));

		name = new JLabel("Name: ");
		name.setBounds(50, 90, 80, 30);

		errorBar = new JLabel("");
		errorBar.setBounds(260, 90, 150, 30);

		nametxt = new JTextField();
		nametxt.setBounds(100, 90, 150, 30);

		//newGameTitle.setForeground(Color.BLACK);

		start = new JButton("Start");
		start.setBounds(160, 200, 80, 30);

		back = new JButton("Back");
		back.setBounds(40, 200, 80, 30);

		add(name);
		add(errorBar);
		add(diffPanel);
		add(nametxt);
		add(newGameTitle);
		add(start);
		add(back);
	}

	public void initializeRadioButtons(JPanel panel)
	{
		easy = new JRadioButton("Easy");
		med = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");

		diffGroup = new ButtonGroup();
		diffGroup.add(easy);
		diffGroup.add(med);
		diffGroup.add(hard);

		easy.setSelected(true);

		diffPanel.add(easy);
		diffPanel.add(med);
		diffPanel.add(hard);
	}

	public String getDiff()
	{
		if(easy.isSelected() == true)
		{
			return "easy";
		}

		if(med.isSelected() == true)
		{
			return "med";
		}

		if(hard.isSelected() == true)
		{
			return "hard";
		}
		return "";
	}
}