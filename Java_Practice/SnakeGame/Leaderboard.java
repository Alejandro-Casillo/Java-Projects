import java.io.*;
import javax.swing.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;

public class Leaderboard 
{
	private JScrollPane scrollpane;
	//private JPanel 
	private ArrayList<Player> playerData = new ArrayList<>();
	private Scanner scanner;
	private String filename;
	//private LeaderboardGUI lgui = new LeaderboardGUI();

	public Leaderboard(String diff)
	{
		switch(diff)
		{
			case "easy": 
				filename = "leaderboardEasy.txt";
				break;
			case "med":
				filename = "leaderboardMed.txt";
				break;
			case "hard":
				filename = "leaderboardHard.txt";
				break;
		}
		readFile(filename);
	}

	public void writeFile()
	{
		try
		{
			PrintWriter fw = new PrintWriter(filename);
			fw.print("");
			fw.close();
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
			for(int i = 0; i < playerData.size(); i++)
			{
				bw.write(playerData.get(i).getName() + "\t" + playerData.get(i).getScore() + "\t" + playerData.get(i).getDiff() + "\n"); 
			}
			bw.close();
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}

	public void readFile(String filename)
	{
		try
		{
			scanner = new Scanner(new File(filename));
			while(scanner.hasNext())
			{
				String line = scanner.nextLine();
				String[] arr = line.split("\t"); 
				int score = Integer.parseInt(arr[1]);
				Player player = new Player(arr[0], score, arr[2]);
				playerData.add(player);
			}
		}
		catch(IOException ex)
		{
			JOptionPane.showMessageDialog(null, "File not found");
		}
	}

	public void addtoleaderBoard(Player pl)
	{
		boolean found = false;
		Iterator it = playerData.iterator();
		int i = 0;
		while(it.hasNext())
		{
			Player play = (Player)it.next();
			if(play.getScore() < pl.getScore())
			{
				if(!found)
				{
					while(it.hasNext())
					{
						if(play.getName().equals(pl.getName()))
						{
							it.remove();
						}
						break;
					}
					playerData.add(i, pl);
					found = true;
					break;
				}
			}
			else if(play.getName().equals(pl.getName()))
			{
				found = true; 
				break;
			}
			i++;
		}
		if(!found)
		{
			playerData.add(pl);
		}
		writeFile();
	}
}