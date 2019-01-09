/* 
 * Milan Gulati
 * 
 * This class is used as the order holder for each value in the hashmap
 * Each order will be a node in the linked list
 * all seats in a single order will be an arraylist of TheaterSeats
 */
package Tickets;

import java.util.*;

public class LinkedOrders
{
	private BaseOrder head;					//head of linked list
	private int count;						//keeps track of number of nodes
	
	LinkedOrders()
	{
		head = new BaseOrder();
		count = 0;
	}
	
	public void add(int audNumber, Auditorium aud, TheaterSeat startSeat, int row, char seat, int numA, int numC, int numS)
	{
		BaseOrder temp = new BaseOrder(audNumber, aud, startSeat, row, seat, numA, numC, numS);
		BaseOrder curr = head;
		
		//go to end of linked list
		while(curr.getNext() != null)
			curr = curr.getNext();
		
		curr.setNext(temp);
		count++;
	}
	
	public boolean remove(int index)
	{
		BaseOrder curr = head;	//current pointer
		
		if(head != null)
		{
			for(int i = 0; i < index; i++)
			{
				if(curr.getNext() == null)
					return false;
				curr = curr.getNext();
			}
			
			curr.setNext(curr.getNext().getNext());
			
			count--;
			return true;
		}
		
		return false;
	}
	
	public BaseOrder getNode(int index)
	{
		if(index < 0)
			return null;
		BaseOrder curr = null;
		
		if(head != null)
		{
			curr = head.getNext();
			for(int i = 0; i < index; i++)
			{
				if(curr.getNext() == null)
				{
					return null;
				}
				curr = curr.getNext();
			}
			return curr;
		}
		return curr;
	}
	
	//accessors
	public BaseOrder getHead() {return head;}
	
	public int getCount() {return count;}
	
	//mutators
	public void setHead(BaseOrder h) {head = h;}
	
	public void setCount(int c) {count = c;}
}

class BaseOrder
{
	private BaseOrder next;
	private ArrayList<TheaterSeat> seatList;	//holds the seats for the order
	private int numA;
	private int numC;
	private int numS;
	private int audNum;							//stores the number of the auditorium
	
	BaseOrder() 
	{
		next = null;
		seatList = new ArrayList<TheaterSeat>(100);
		numA = 0;
		numC = 0;
		numS = 0;
	}
	
	BaseOrder(int audNumber, Auditorium aud, TheaterSeat startSeat, int row, char seat, int adultQ, int childQ, int seniorQ)
	{
		seatList = new ArrayList<TheaterSeat>(100);
		
		numA = adultQ;
		numC = childQ;
		numS = seniorQ;
		
		audNum = audNumber;
		
		TheaterSeat current = aud.getFirst();	//initialize the current seat pointer to first seat
		
		while(current.getRow() < row)			//while loop - move current to correct row
			current = current.getDown();		//move current down a node
		
		while(current.getSeat() < seat)			//while loop - move current to correct seat column
			current = current.getRight();		//move current to the right a node
		
		int totalQ = numA + numC + numS;		//totalQ used to determine how many seats there will be in order
		
		for(int i = 0; i < totalQ; i++)
		{
			seatList.add(current);					//add current seat to the reservation
			current = current.getRight();			//move to next seat in order
		}
	}
	
	//accessors
	public BaseOrder getNext() {return next;}
	
	public ArrayList<TheaterSeat> getSeatList() {return seatList;}
	
	public int getSeatListSize() {return seatList.size();}
	
	public int getAdult() {return numA;}
	
	public int getChild() {return numC;}
	
	public int getSenior() {return numS;}
	
	public int getAudNum() {return audNum;}
	
	
	//mutators
	public void setNext(BaseOrder n) {next = n;}
	
	public void setSeatList(ArrayList<TheaterSeat> list) {seatList = list;}
	
	public void setAdult(int num) {numA = num;}
	
	public void setChild(int num) {numC = num;}
	
	public void setSenior(int num) {numS = num;}
	
	public void setAudNum(int num) {audNum = num;}
}
