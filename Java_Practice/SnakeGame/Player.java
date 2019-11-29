
public class Player
{
	private String name, diff;
	private int score;

	public Player(String name, int score, String diff) 
	{
		this.name = name;
		this.score = score;
		this.diff = diff;
	}

	public String getName()
	{
		return name;
	}

	public int getScore()
	{
		return score;
	}

	public void updateScore(int value)
	{
		score += value;
	}

	public void setScore(int score)
	{
		this.score = score;
	}

	public String getDiff()
	{
		return diff;
	}
}