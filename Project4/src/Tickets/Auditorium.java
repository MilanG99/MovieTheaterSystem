/*
 * Milan Gulati
 */

package Tickets;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Auditorium 
{
	//member variables
	private TheaterSeat first;				//acts as the head pointer to the linked list
	
	
	/*
	 * Auditorium Constructor
	 * Builds grid that represents auditorium file
	 * populates it with theater seats
	 * makes theater seats point to the seats around it
	 */
	Auditorium(File input) throws IOException
	{
		int numRows = 0, numSeats = 0;
		Scanner readF = new Scanner(input);
		
		while(readF.hasNext())
		{
			String temp = readF.next();			//turn line into temp string
			char[] line = temp.toCharArray();	//turn string into char array
			
			numSeats = line.length;				//find number of chars = columns
			
			numRows++;							//increment number of rows
		}
		readF.close();
		
		
		TheaterSeat[][] grid = new TheaterSeat[numRows][numSeats];
		
		//First, initialize the value of each node
		//appropriate helper functions will retrieve values such as
		//seat letter, seat availability, and seat type (A,C, or S)
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numSeats; j++)
			{
				grid[i][j] = new TheaterSeat(i, getLetter(j), checkSeatAvail(i, j, input), readSeatType(i ,j, input));
			}
		}
		
		//Now, make nodes point to each other in order to form a grid of connected nodes
		//seats on edges of the grid cannot point to each other, must point to null
		for(int i = 0; i < numRows; i++)
		{
			for(int j = 0; j < numSeats; j++)
			{
				if(i < numRows - 1)							//if able to set down pointer
					grid[i][j].setDown(grid[i + 1][j]);
				if(i > 1)									//if able to set up pointer
					grid[i][j].setUp(grid[i - 1][j]);
				if(j < numSeats - 1)						//if able to set right pointer
					grid[i][j].setRight(grid[i][j + 1]);
				if(j > 1)									//if able to set left pointer
					grid[i][j].setLeft(grid[i][j - 1]);
			}
		}
		setFirst(grid[0][0]);								//set first pointer to first seat one row one
	}
	
	
	//mutators
	public void setFirst(TheaterSeat f)
	{
		first = f;
	}
	
	
	//accessors
	public TheaterSeat getFirst()
	{
		return first;
	}
	
	
	//functions pertinent to the auditorium
	
	/*
	 * Function builds a 2d char array
	 * used by helper functions of the Auditorium constructor
	 * returns a 2d char array
	 */
	public static char[][] readAuditorium(File aud) throws IOException
	{
		char[][] auditorium = new char [100][26];	//create new char array with maximum size
		
		int rows = 0, columns = 0;
		Scanner input = new Scanner(aud);
		
		while(input.hasNext())
		{
			String temp = input.next();			//turn line into temp string
			char[] line = temp.toCharArray();	//turn string into char array
			
			columns = line.length;				//find number of chars = columns
			
			for(int i = 0; i < columns; i++)	//go through entire row
			{
				auditorium[rows][i] = line[i];	//assign the letter in the char array to that in auditorium
			}
			
			rows++;								//go to next row
		}
		input.close();
		
		return auditorium;
	}
	
	
	/*
	 * Checks if an individual seat is available
	 * Used for calling base node conductor
	 */
	public static boolean checkSeatAvail(int row, int seat, File aud) throws IOException
	{
		char [][] auditorium = readAuditorium(aud);
		char temp = auditorium[row][seat];
		
		if(temp != '.')			//if seat occupied return TRUE
		{
			return true;
		}
		
		return false;			//otherwise seat is empty return FALSE
	}
	
	/*
	 * Returns the type of seat specified at a certain row and column
	 */
	public static char readSeatType(int row, int seat, File aud) throws IOException
	{
		char [][] auditorium = readAuditorium(aud);
		
		char temp = auditorium[row][seat];
		
		return temp;
	}
	
	/*
	 * returns the letter of the seat column
	 */
	public static char getLetter(int i)
	{
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char let = abc.charAt(i);
		
		return let;
	}
	
	/*
	 * returns the number of rows in the auditorium
	 */
	public static int getNumRows(Auditorium aud)
	{
		int numRows = 1;						//variable keeps track of number of rows
		
		TheaterSeat current = aud.getFirst();	//initialize current seat to the first seat
		
		while(current.getDown() != null)		//while not past all the rows
		{
			current = current.getDown();		//set current pointer to next row
			numRows++;							//increment number of rows
		}
		
		return numRows;						//return number of rows
	}
	
	/*
	 * returns the number of columns in the auditorium
	 */
	public static int getNumSeats(Auditorium aud)
	{
		int numSeats = 1;						//variable keeps track of number of seats in a row
		
		TheaterSeat current = aud.getFirst();	//initialize current seat to the first seat
		
		while(current.getRight() != null)		//while not past all the columns
		{
			current = current.getRight();		//set the current pointer to next column
			numSeats++;							//increment the number of seats
		}
		
		return numSeats;					//return number of seats
	}
}
