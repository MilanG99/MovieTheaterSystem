/*
 * Milan Gulati
 */

package Tickets;

public abstract class BaseNode
{
	//member variables
	private int row;					//row number
	private char seat;					//seat letter in row
	private boolean reserved;			//indicates if seat is reserved(#) or unreserved(.)
	private char type;					//indicates type of seat (A,C,S)
	
	
	//overloaded constructor - to be called by derived classes
	BaseNode(int r, char s, boolean reserve, char t)
	{
		row = r;
		seat = s;
		reserved = reserve;
		type = t;
	}
	
	
	//mutators
	public void setRow(int r)
	{
		row = r;
	}
	
	public void setSeat(char s)
	{
		seat = s;
	}
	
	public void setReserved(boolean reserve)
	{
		reserved = reserve;
	}
	
	public void setType(char t)
	{
		type = t;
	}
	
	
	//accessors
	public int getRow()
	{
		return row;
	}
	
	public char getSeat()
	{
		return seat;
	}
	
	public boolean getReserved()
	{
		return reserved;
	}
	
	public char getType()
	{
		return type;
	}
}
