


public class Door
{

	boolean isOpen;

	public Door(boolean condition)
	{
		this.isOpen = condition;
	}

	public Door()
	{
		this.isOpen = 0;
	}

	public setDoor(boolean condition)
	{
		this.isOpen = condition;
	}

	public isDoorOpen()
	{
		return this.isOpen;
	}

}