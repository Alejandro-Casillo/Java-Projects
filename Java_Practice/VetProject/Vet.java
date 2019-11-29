import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;
//Name, Age, Species, Fur_color, Eye_color, ID_num

public class Vet
{
	public ArrayList<Animal> animals = new ArrayList<>();
	private Scanner scanner;

	public Vet(String filename)
	{
		readFile(filename);
	}

	public void addAnimal(Animal an)
	{
		animals.add(an);
		writeFile(an);
	}

	public void removeAnimal(String id)
	{
		boolean flag = false;
		Scanner scan = new Scanner(System.in);
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
		for(Iterator <Animal> itr = animals.iterator(); itr.hasNext();)
		{
			Animal an = itr.next();
			if(an.getID().equals(id))
			{
				flag = true;
				System.out.println("\n" + an + "\n");
				System.out.println("Do you wish to remove this animal from the database? y/n");
				String ans = scan.nextLine();
				if(ans.equals("y"))
				{
					itr.remove();
					System.out.println("\n" + "Animal has been successfully removed from the database.");
				}
				else
				{
					writeFile(an);
				}
			}
			else
			{
				writeFile(an);
			}
		}
		if(flag == false)
		{
			System.out.println("Animal was not found");
		}
	}

	public Animal getAnimalbyID(String id)
	{
		for(Animal a : animals)
		{
			if(a.getID().equals(id))
			{
				return a;
			}
		}
		return null;
	}

	public void writeFile(Animal an)
	{
		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("animals.txt", true));
			bw.write(an.getName() + "," + an.getAge() + "," + an.getSpecies() + "," + an.getColor() + "," + an.getEyeColor() + "," + an.getID() + "\n");
			bw.close();
		}
		catch(IOException ex)
		{
			System.out.println("File not found");
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
				String[] arr = line.split(",");
				int age = Integer.parseInt(arr[1]);
				Animal an = new Animal(arr[0], age, arr[2], arr[3], arr[4], arr[5]);
				animals.add(an);
			}
		}
		catch(FileNotFoundException e)
		{
			System.out.println("File does not exist!!");
		}
	}

	public void showAnimals()
	{
		for(Animal a: animals)
		{
			System.out.println("\n" + a + "\n");
		}
	}

	public String makeID(String name, String species, String color)
	{
		String id = "";
		Random rand = new Random();
		while(true)
		{
			id += name.substring(0, 2);
			id += rand.nextInt(9) + "";
			id += species.substring(0, 1);
			id += rand.nextInt(9) + "";
			id += color.substring(0, 1);
			if(!idExist(id))
			{
				break;
			}
		}
		return id;
	}

	public boolean idExist(String id)
	{
		for(Animal a : animals)
		{
			if(a.getID().equals(id))
			{
				return true;
			}
		}
		return false;
	}
}