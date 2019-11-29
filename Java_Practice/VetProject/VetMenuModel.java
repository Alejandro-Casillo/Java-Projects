import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;

public class VetMenuModel
{
	private VetMenuGUI gui;
	private Vet vet;
	private String fur_color, eye_color, species;
	private Border border;
	private ArrayList<JPanel> scrollPanels; 

	public VetMenuModel()
	{
		gui = new VetMenuGUI();
		vet = new Vet("animals.txt");
		scrollPanels = new ArrayList<>();
		initializeButtonListeners(gui.furButtons, gui.eyeButtons);
		border = gui.nametxt.getBorder();

		gui.searchBar.addKeyListener(new KeyListener()
		{
			public void keyReleased(KeyEvent e)
			{
				char c = e.getKeyChar();
				if(Character.isLetter(c) || e.getKeyCode() == e.VK_BACK_SPACE)
				{
					filter(gui.searchBar.getText(), gui.scrollPanel); 
				}
			}
			public void keyPressed(KeyEvent e)
			{
				/*char c = e.getKeyChar();
				if(Character.isLetter(c))
				{
					filter(gui.searchBar.getText() + c, gui.scrollPanel); 
				}*/
			}
			public void keyTyped(KeyEvent e)
			{

			}
		});

		gui.add.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.remove(gui.main_menu);
				gui.add_view.add(gui.back);
				gui.back.setBounds(0, 430, 80, 30);
				gui.add(gui.add_view);
				gui.repaint();
				gui.revalidate();
			}
		});

		gui.remove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.remove(gui.main_menu);
				gui.setBounds(gui.getX(), gui.getY(), 500, 150);
				gui.remove_view.add(gui.back);
				gui.back.setBounds(10, 90, 80, 30);
				gui.add(gui.remove_view);
				gui.repaint();
				gui.revalidate();
			}
		});

		gui.back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.getContentPane().removeAll();
				gui.add(gui.main_menu);
				gui.remove_id.setBorder(border);
				clearAddInputs();
				clearRemoveInputs();
				clearSearchInputs();
				gui.update_bar.setText("");
				gui.setBounds(gui.getX(), gui.getY(), 500, 500);
				gui.repaint();
				gui.revalidate();
			}
		});

		gui.get_info.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.remove(gui.main_menu);
				gui.info_view.add(gui.back);
				gui.back.setBounds(70, 420, 80, 30);
				gui.add(gui.info_view);
				gui.repaint();
				gui.revalidate();
			}
		});

		gui.see_database.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.remove(gui.main_menu);
				gui.info_view.add(gui.back);
				gui.setBounds(gui.getX(), gui.getY(), 500, 500);
				gui.add(gui.info_view);
			}
		});

		gui.exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				gui.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
			}
		});

		gui.agetxt.addKeyListener(new KeyAdapter()
			{
				@Override
				public void keyTyped(KeyEvent e)
				{
					char c = e.getKeyChar();
					if(!Character.isDigit(c) || c == '.')
					{
						e.consume();
					}
				}
			});

		gui.submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = gui.nametxt.getText();
				int age = -1; 
				species = String.valueOf(gui.box.getSelectedItem());
				boolean missing = false;
				
				if(name.equals(""))
				{
					missing = true;
					gui.nametxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					gui.nametxt.setBorder(border);
				}

				if(gui.agetxt.getText().equals(""))
				{
					missing = true;
					gui.agetxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					gui.agetxt.setBorder(border);
					age = Integer.parseInt(gui.agetxt.getText());
				}

				if(species.equals(""))
				{
					missing = true;
					gui.box.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					gui.box.setBorder(null);
				}

				if(fur_color == null)
				{
					missing = true;
					gui.furPanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					gui.furPanel.setBorder(null);
					if(fur_color.startsWith("Other"))
					{
						fur_color = "Other: " + gui.othertxt.getText();
						String[] sp = fur_color.split(" ");

						if(sp.length == 1)
						{
							missing = true;
							gui.othertxt.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
						}
						else
						{
							gui.othertxt.setBorder(border);
						}
					}
				}

				if(eye_color == null)
				{
					missing = true;
					gui.eyePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
				}
				else
				{
					gui.eyePanel.setBorder(null);
				}
				
				if(missing)
				{
					gui.error_bar.setText("Error: Missing inputs");
					return;
				}
				else
				{
					Animal an;
					if(fur_color.startsWith("Other"))
					{
						String furColor1 = fur_color.substring(6);
						an = new Animal(name, age, species, furColor1, eye_color, vet.makeID(name, species, furColor1));
						vet.addAnimal(an);

					}
					else
					{
						an = new Animal(name, age, species, fur_color, eye_color, vet.makeID(name, species, fur_color));
						vet.addAnimal(an);
					}

					if(vet.animals.size() % 2 == 0)
						{
							JPanel pa = gui.aniPanel(an);
							pa.setBackground(Color.lightGray);
							gui.scrollPanel.add(pa);
							scrollPanels.add(pa);
						}
					else
						{
							JPanel pa = gui.aniPanel(an);
							gui.scrollPanel.add(pa);
							scrollPanels.add(pa);
						}
					gui.repaint();
					gui.revalidate();
					JOptionPane.showMessageDialog(null, name + " has been added to the database.");
					clearAddInputs();
					gui.remove(gui.add_view);
					gui.add(gui.main_menu);
					gui.repaint();
					gui.revalidate();
				}
			}
		});

		/*for(int i = 0; i < scrollPanels.size(); i++)
		{
			scrollPanels.get(i).addMouseListener(new MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					System.out.println(vet.animals.get(i));
					//seeAnimal(animals[i]);
				}
				public void mouseExited(MouseEvent e)
				{

				}
				public void mouseEntered(MouseEvent e)
				{

				}
				public void mouseReleased(MouseEvent e)
				{

				}
				public void mouseClicked(MouseEvent e)
				{

				}
			});
		}*/

		gui.searchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				filter(gui.searchBar.getText(), gui.scrollPanel);
			}
		});

		gui.doneButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String id = gui.remove_id.getText();
				if(id.equals(""))
				{
					gui.remove_id.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
					gui.update_bar.setForeground(Color.RED);
					gui.update_bar.setText("Error: Missing inputs");
				}
				else
				{
					if(!vet.idExist(id))
					{
						gui.remove_id.setBorder(border);
						gui.update_bar.setForeground(Color.RED);
						gui.update_bar.setText("Animal was not found");
					}
					else
					{
						removeAnimal(id);
						gui.remove_id.setBorder(border);
						gui.remove(gui.remove_view);
						gui.add(gui.main_menu);
						gui.setBounds(gui.getX(), gui.getY(), 500, 500);
						gui.repaint();
						gui.revalidate();
					}
				}
			}
		});

		/*for(int i = 0; i < gui.infoButtons.size(); i++)
		{
			gui.jButtonArr[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JOptionPane.showMessageDialog(null, "Animal information");
				}
			});
		}*/
	}

	public void initializeButtonListeners(JRadioButton[] furButtons, JRadioButton[] eyeButtons)
	{
		int flen = furButtons.length;
		int elen = eyeButtons.length; 
		int i = 0;
		int last = flen - 1;
		while(i < flen || i < elen)
		{
			if(i < flen)
			{
				if(i == last)
				{
					furButtons[i].addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							gui.othertxt.setEditable(true);
							fur_color = "Other: ";
						}
					});
				}
				else
				{
					furButtons[i].addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent e)
						{
							gui.othertxt.setEditable(false);
							JRadioButton j = (JRadioButton) e.getSource();
							fur_color = j.getText();
						}
					});
				}
			}
			if(i < elen)
			{
				eyeButtons[i].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JRadioButton j = (JRadioButton) e.getSource();
						eye_color = j.getText();
					}
				});
			}
			i++;
		}
	}

	public void removeAnimal(String id)
	{
		boolean flag = false;
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("animals.txt", false));
			bw.write("");
			bw.close();
		}
		catch(IOException ex)
		{
			System.out.println("File not found");
		}
		for(Iterator <Animal> itr = vet.animals.iterator(); itr.hasNext();)
		{
			Animal an = itr.next();
			if(an.getID().equals(id))
			{
				flag = true;
				int ans = JOptionPane.showConfirmDialog(null, "Do you wish to remove the following animal from the database?\n \n" + an.toString(), "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(ans == JOptionPane.YES_OPTION)
				{
					JOptionPane.showMessageDialog(null, an.getName() + " has been successfully removed from the database.");
					itr.remove();
				}
				else
				{
					vet.writeFile(an);
				}
			}
			else
			{
				vet.writeFile(an);
			}
		}
		if(flag == false)
		{
			System.out.println("Animal was not found");
		}
	}

	public void clearAddInputs()
	{
		eye_color = null;
		fur_color = null;
		species = null;
		gui.nametxt.setText("");
		gui.agetxt.setText("");
		gui.othertxt.setText("");
		gui.box.setSelectedIndex(0);
		gui.fGroup.clearSelection();
		gui.eGroup.clearSelection();
		gui.furPanel.setBorder(null);
		gui.eyePanel.setBorder(null);
		gui.nametxt.setBorder(border);
		gui.agetxt.setBorder(border);
		gui.box.setBorder(null);
		gui.othertxt.setBorder(border);
		gui.error_bar.setText("");
	}

	public void clearRemoveInputs()
	{
		gui.error_bar.setText("");
		gui.remove_id.setBorder(border);
		gui.remove_id.setText("");
	}

	public void clearSearchInputs()
	{
		gui.searchBar.setText("");
		filter(gui.searchBar.getText(), gui.scrollPanel);
	}

	public void filter(String search_bar, JPanel scroller)
	{
		ArrayList<Animal> ans = new ArrayList<>(); 
		String input = search_bar;
		scroller.removeAll();
		gui.repaint();
		gui.revalidate();
		int i = 0;

		for(Animal a : vet.animals)
		{
			if(a.getName().startsWith(input)) 
			{
				JPanel aniP = gui.aniPanel(a); 
				scroller.add(aniP); 
				gui.repaint();
				gui.revalidate();
				i++;
			}
			else if(input.equals("")) 
			{
				JPanel aniP = gui.aniPanel(a); 
				scroller.add(aniP); 
				gui.repaint(); 
				gui.revalidate();
				i++; 
			}
		}
		if(i == 0) 
		{
			JLabel lbl = new JLabel("No Results found");
			scroller.add(lbl);
			gui.repaint();
			gui.revalidate();
		} 
	}
}
