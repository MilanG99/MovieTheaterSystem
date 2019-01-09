/*
 * Milan Gulati
 */

package Tickets;

public class TheaterSeat extends BaseNode
{
	//member variables
	private TheaterSeat up;						//seat that is above
	private TheaterSeat down;					//seat that is under
	private TheaterSeat left;					//seat that is to the left
	private TheaterSeat right;					//seat that is to the right
	
	
	//overloaded constructor
	TheaterSeat(int r, char s, boolean reserve, char t, TheaterSeat up, TheaterSeat down, TheaterSeat left, TheaterSeat right) 
	{
		super(r, s, reserve, t);
		
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
	}
	
	//constructor
	TheaterSeat(int r, char s, boolean reserve, char t)
	{
		super(r, s, reserve, t);
	}
	
	
	//mutators
	public void setUp(TheaterSeat up)
	{
		this.up = up;
	}
	
	public void setDown(TheaterSeat down)
	{
		this.down = down;
	}
	
	public void setLeft(TheaterSeat left)
	{
		this.left = left;
	}
	
	public void setRight(TheaterSeat right)
	{
		this.right = right;
	}
	
	
	//accessors
	public TheaterSeat getUp()
	{
		return up;
	}
	
	public TheaterSeat getDown()
	{
		return down;
	}
	
	public TheaterSeat getLeft()
	{
		return left;
	}
	
	public TheaterSeat getRight()
	{
		return right;
	}
}
