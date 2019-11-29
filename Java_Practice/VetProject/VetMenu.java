import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class VetMenu
{
	private String menu; 
	private boolean run;
	private Scanner scanner;
	private Vet vet;

	public VetMenu()
	{
		run = true;
		menu = "\n1. Add animal to database.\n2. Remove animal from database.\n3. Get animal info.\n4. Show animal database.\n5. Exit";
	}

	public VetMenu(String filename)
	{
		this();
		vet = new Vet(filename);
	}

	public int getNumeric(int start, int end)
	{
		while(true)
		{
			try
			{
				int input = scanner.nextInt();
				if(input >= start && input <= end)
				{
					scanner.nextLine();
					return input;
				}
				else
				{
					System.out.println("Number out of range\nPlease enter another number: ");
				}
			}
			catch(InputMismatchException ex)
			{
				scanner.nextLine();
				System.out.println("\nInvalid input. \nPlease enter another number");
			}
		}
	}

	public void run()
	{
		scanner = new Scanner(System.in);

		while(run)
		{
			System.out.println(menu);
			int input = getNumeric(1, 5);
			if(input == 1)
			{
				System.out.println("What is the animal's name?");
				String name = scanner.nextLine();

				int age;
				String species = "";
				String fur_color = "";
				String eye_color = "";

				System.out.println("What is the animal's age?");
				age = getNumeric(0, 100);

				System.out.println("Please select a species: \n");
				System.out.println("1. Dog");
				System.out.println("2. Cat");
				System.out.println("3. Fish");
				System.out.println("4. Human");
				int species_input = getNumeric(1, 4);

				if(species_input == 1)
				{
					species = "Dog";
				}
				else if(species_input == 2)
				{
					species = "Cat";
				}
				else if(species_input == 3)
				{
					species = "Fish";
				}
				else if(species_input == 4)
				{
					species = "Human";
				}

				System.out.println("What is the animal's fur color?");
				fur_color = scanner.nextLine();

				System.out.println("What is the animal's eye color?");
				eye_color = scanner.nextLine();

				Animal an = new Animal(name, age, species, fur_color, eye_color, vet.makeID(name, species, fur_color));
				vet.addAnimal(an);
				System.out.println("\n" + an + "\n");
			}
			else if(input == 2)
			{
				System.out.println("Please enter ID number"); 
				String id = scanner.nextLine();
				vet.removeAnimal(id);
			}
			else if(input == 3)
			{
				System.out.println("Please enter ID number");
				String id = scanner.nextLine();
				System.out.println("\n" + vet.getAnimalbyID(id) + "\n");
			}
			else if(input == 4)
			{
				vet.showAnimals();
			}
			else if(input == 5)
			{
				run = false;
			}
		}
	}
}
