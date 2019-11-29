import java.util.Random;

public class Animal
{

	private String name, species, color, ecolor, id;
	private int age;

	/*public Animal(String name, int age, String species, String color, String ecolor)
	{
		this.name = name;
		this.age = age;
		this.species = species;
		this.color = color;
		this.ecolor = ecolor;
		makeID();
	}*/

	public Animal(String name, int age, String species, String color, String ecolor, String id)
	{
		this.name = name;
		this.age = age;
		this.species = species;
		this.color = color;
		this.ecolor = ecolor;
		this.id = id;
	}

	public String toString()
	{
		return "Name: " + name + "\nAge: " + age + "\nSpecies: " + species + "\nFur Color: " + color + "\nEye Color: " + ecolor + "\nID: " + id;
	}

	public String getID()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getSpecies()
	{
		return species;
	}

	public String getColor()
	{
		return color;
	}

	public String getEyeColor()
	{
		return ecolor;
	}

	public int getAge()
	{
		return age;
	} 
}