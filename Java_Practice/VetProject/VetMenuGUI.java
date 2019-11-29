import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VetMenuGUI extends JFrame
{
	public JPanel main_menu, add_view, remove_view, info_view, database, scrollPanel, spePanel, furPanel, eyePanel;
	public JLabel title, name, age, species, other, fur_color, e_color, update_bar, error_bar;
	public JTextField nametxt, agetxt, speciestxt, othertxt, remove_id, searchBar;
	public JButton add, remove, get_info, see_database, exit, back, submit, searchButton, doneButton, getAnInfo;
	public String[] petStrings = {"", "Dog", "Cat", "Rabbit", "Pig", "Fish", "Bird"};
	public String[] furColors = {"Black", "Gray", "White", "Golden", "Orange", "Other"};
	public String[] eyeColors = {"Brown", "Green", "Blue", "Black", "Red", "Hazel"};
	public JRadioButton[] furButtons;
	public JRadioButton[] eyeButtons;
	public JScrollPane scrollpane;
	public final int JFRAME_WIDTH = 500;
	public final int JFRAME_HEIGHT = 500; 
	public JComboBox box;
	public ButtonGroup fGroup, eGroup;
	public Vet vet;
	public ArrayList<JButton> infoButtons;
	public int colorID = 0;

	public VetMenuGUI()
	{
		setBounds(100, 100, JFRAME_HEIGHT, JFRAME_WIDTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initializeMenu();
		initializeAdd();
		initializeRemove();
		vet = new Vet("animals.txt");
		initializeInfo();
		add(main_menu);
		repaint();
		revalidate();
		setVisible(true);	
	}

	public void initializeMenu()
	{
		main_menu = new JPanel();
		JPanel mini_menu = new JPanel();
		add = new JButton("Add Animal");
		remove = new JButton("Remove Animal");
		get_info = new JButton("Search for animal");
		see_database = new JButton("See animal database");
		exit = new JButton("Exit");
		title = new JLabel("Vet Menu");
		title.setBounds(215, 50, 150, 150);
		title.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		back = new JButton("Back");

		main_menu.setLayout(null);
		main_menu.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		main_menu.setVisible(true);

		mini_menu.setBounds(150, 150, 200, 200);
		mini_menu.setVisible(true);
		mini_menu.setLayout(new GridLayout(4, 1));
		mini_menu.setBackground(Color.RED);

		/*add.addMouseListener(new MouseListener() all these listeners go in the VetMenuModel.java
		{
			public void mousePressed(MouseEvent e)
			{

			}
			public void mouseExited(MouseEvent e)
			{
				//add.setBackground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e)
			{
				add.setBackground(Color.lightGray);
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
			public void mouseClicked(MouseEvent e)
			{
			
			}
		});

		remove.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{

			}
			public void mouseExited(MouseEvent e)
			{
				//remove.setBackground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e)
			{
				remove.setBackground(Color.lightGray);
				repaint();
				revalidate();
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
			public void mouseClicked(MouseEvent e)
			{
			
			}
		});

		get_info.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent e)
			{

			}
			public void mouseExited(MouseEvent e)
			{
				//mini_menu.setBackground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e)
			{
				//add.setBackground(Color.lightGray);
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
			public void mouseClicked(MouseEvent e)
			{
			
			}
		});

		exit.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent e)
			{

			}
			public void mouseExited(MouseEvent e)
			{
				//exit.setBackground(Color.WHITE);
			}
			public void mouseEntered(MouseEvent e)
			{
				exit.setBackground(Color.lightGray);
			}
			public void mouseReleased(MouseEvent e)
			{
				
			}
			public void mouseClicked(MouseEvent e)
			{
			
			}
		});*/

		mini_menu.add(add);
		mini_menu.add(remove);
		mini_menu.add(get_info);
		//mini_menu.add(see_database);
		mini_menu.add(exit);

		main_menu.add(title);
		main_menu.add(mini_menu);
	}
	public void initializeAdd()
	{
		add_view = new JPanel();
		add_view.setLayout(null);
		add_view.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		//add_view.setBackground(Color.RED);

		JPanel miniadd = new JPanel();
		miniadd.setLayout(null);
		miniadd.setBounds(20, 50, 300, 70);
		//miniadd.setBackground(Color.GREEN);

		JPanel mini2 = new JPanel();
		mini2.setLayout(new GridLayout(1, 3));
		mini2.setBounds(20, 130, 450, 250);
		mini2.setBackground(Color.YELLOW);

		spePanel = new JPanel();
		spePanel.setLayout(null);
		//spePanel.setBackground(Color.GRAY);

		furPanel = new JPanel();
		furPanel.setLayout(null);
		//furPanel.setBackground(Color.GREEN);

		eyePanel = new JPanel();
		eyePanel.setLayout(null);
		//eyePanel.setBackground(Color.RED); 

		initializeAddHelper(furPanel, eyePanel);

		name = new JLabel("Name:");
		age = new JLabel("Age:");
		species = new JLabel("Species: ");
		fur_color = new JLabel("Fur Color: "); 
		e_color = new JLabel("Eye Color: ");
		error_bar = new JLabel();

		box = new JComboBox(petStrings);
		box.setBounds(0, 40, 100, 30);
		box.setSelectedIndex(0); 

		name.setBounds(0, 0, 40, 30);
		age.setBounds(0, 40, 40, 30);
		species.setBounds(0, 0, 80, 30); 
		fur_color.setBounds(0, 0, 80, 30);
		e_color.setBounds(0, 0, 80, 30);
		error_bar.setBounds(175, 430, 150, 30);
		error_bar.setHorizontalAlignment(SwingConstants.CENTER);
		error_bar.setForeground(Color.RED);

		nametxt = new JTextField();
		agetxt = new JTextField();
		othertxt = new JTextField();

		submit = new JButton("Submit");
		submit.setBounds(400, 430, 80, 30);

		nametxt.setBounds(40, 0, 100, 30);
		agetxt.setBounds(40, 40, 100, 30);
		othertxt.setBounds(0, 210, 100, 30);
		othertxt.setEditable(false);

		spePanel.add(species);
		spePanel.add(box);
		furPanel.add(fur_color);
		furPanel.add(othertxt);
		eyePanel.add(e_color);

		miniadd.add(name);
		miniadd.add(age); 
		miniadd.add(nametxt);
		miniadd.add(agetxt);

		mini2.add(spePanel);
		mini2.add(furPanel);
		mini2.add(eyePanel);

		add_view.add(miniadd);
		add_view.add(mini2);
		add_view.add(submit);
		add_view.add(error_bar);
	}

	public void initializeRemove() //asks for string id and removes animal from database
	{
		remove_view = new JPanel();
		remove_view.setLayout(null);
		remove_view.setBounds(0, 0, getWidth(), getHeight());

		JLabel remove_label = new JLabel("Please enter the id number of the animal you wish to remove: ");
		remove_label.setBounds(10, 10, 400, 30);

		remove_id = new JTextField();
		remove_id.setBounds(10, 50, 120, 30);

		update_bar = new JLabel();
		update_bar.setBounds(150, 50, 180, 30);

		doneButton = new JButton("Done");
		doneButton.setBounds(100, 90, 80, 30);

		remove_view.add(remove_label);
		remove_view.add(update_bar);
		remove_view.add(remove_id);
		remove_view.add(doneButton);
	}
	public void initializeInfo() //shows one/shows all. Multipurpose. 
	{
		info_view = new JPanel();
		info_view.setBounds(0, 0, JFRAME_WIDTH, JFRAME_HEIGHT);
		info_view.setLayout(null);

		scrollPanel = new JPanel();
		scrollPanel.setBounds(75, 100, 350, 300);
		scrollPanel.setLayout(new BoxLayout(scrollPanel, BoxLayout.Y_AXIS));

		searchBar = new JTextField();
		searchBar.setBounds(75, 50, 250, 30);

		searchButton = new JButton("Search");
		searchButton.setBounds(345, 50, 80, 30);

		scrollpane = new JScrollPane(scrollPanel);
		scrollpane.setBounds(75, 100, 350, 300);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		for(int i = 0; i < vet.animals.size(); i++)
		{
			scrollPanel.add(aniPanel(vet.animals.get(i)));
		}

		info_view.add(scrollpane);
		info_view.add(searchButton);
		info_view.add(searchBar);
	}

	public void initializeAddHelper(JPanel fur, JPanel eye)
	{
		int flen = furColors.length;
		int elen = eyeColors.length; 
		furButtons = new JRadioButton[flen];
		eyeButtons = new JRadioButton[elen];
		fGroup = new ButtonGroup();
		eGroup = new ButtonGroup();
		int i = 0;
		int last = flen - 1;
		while(i < flen || i < elen) 
		{
			if(i < flen)
			{
				furButtons[i] = new JRadioButton(furColors[i]); 
				furButtons[i].setBounds(0, 20+(i*30), 80, 30);
				fGroup.add(furButtons[i]);
				fur.add(furButtons[i]);
			}
			if(i < elen)
			{
				eyeButtons[i] = new JRadioButton(eyeColors[i]);
				eyeButtons[i].setBounds(0, 20+(i*30), 80, 30);
				eGroup.add(eyeButtons[i]);
				eye.add(eyeButtons[i]);
			}
			i++;
		}
	}

	public JPanel aniPanel(Animal an)
	{
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(350, 20));
		//panel.setResizeable(false);
		panel.setLayout(new GridLayout(2, 1));
		JLabel aniName = new JLabel("Name: " + an.getName());
		JLabel aniID = new JLabel("ID number: " + an.getID());
		getAnInfo = new JButton("Get Info");
		//infoButtons.add(getAnInfo);
		getAnInfo.setBounds(250, panel.getHeight()/2, 80, 30);
		aniName.setBounds(0, 0, 80, 30);
		aniID.setBounds(0, 0, 80, 30);
		panel.add(aniName);
		panel.add(aniID);
		Color currentColor;
		if(colorID == 0)
		{
			colorID = 1;
			currentColor = panel.getBackground();
		}
		else
		{
			colorID = 0;
			panel.setBackground(Color.lightGray);
			currentColor = panel.getBackground();
		}
		//panel.add(getAnInfo);

		panel.addMouseListener(new MouseListener()
		{
			public void mousePressed(MouseEvent e)
			{
				panel.setBackground(Color.BLUE.brighter());
			}
			public void mouseExited(MouseEvent e)
			{
				panel.setBackground(currentColor);
			}
			public void mouseEntered(MouseEvent e)
			{
				panel.setBackground(Color.CYAN);
			}
			public void mouseReleased(MouseEvent e)
			{
				panel.setBackground(currentColor);
			}
			public void mouseClicked(MouseEvent e)
			{
				//System.out.println(an);
				JOptionPane.showMessageDialog(null, an.toString());
			}
		});

		return panel;
	}
}