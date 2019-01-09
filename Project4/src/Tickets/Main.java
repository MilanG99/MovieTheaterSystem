/*
 * Milan Gulati
 */

package Tickets;

import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Main
{
	/*
	 * Main function - driver function
	 * responsible for creating hash map and then calling appropriate functions to fill it
	 * prompts for username and password and uses other functions to validate
	 * responsible for main menu interface for both users and admin
	 */
	public static void main(String[] args) throws IOException
	{
		//create hash map and then fill the map
		HashMap<String, Values> map = createHash();

		//create each auditorium object from the three auditorium files
		Auditorium audOne = new Auditorium(new File ("A1.txt"));
		Auditorium audTwo = new Auditorium(new File ("A2.txt"));
		Auditorium audThree = new Auditorium(new File ("A3.txt"));
		
		//now login menu
		String username = "";
		String password = "";
		String neededPass = "";
//		boolean passIncorrect = true;
		int tries = 0;
		
		int menuChoice;
		int audNumber = 0;
		
		Scanner input = new Scanner(System.in);

		do
		{
			if(tries == 0)
			{
				System.out.print("Username: ");
				username = input.next();
				neededPass = (map.get(username)).getPassword();	//now find password
				
				tries = 0;										//set tries equal to zero
			}
			
			System.out.print("Password: ");			//prompt for password
			password = input.next();
			
			if(!password.equals(neededPass))		//if wrong password
			{
//				passIncorrect = true;				//mark password as incorrect
				tries++;							//increment number of tries
				
				if(tries == 3) tries = 0;			//if third try then restart at user name
			}
			else									//if correct password - login to user
			{
				tries = 0;							//set tries to zero
				
				if(username.equals("admin"))		/***** ADMIN LOGGED IN *****/
				{
					do								//do while until admin logs out or exits
					{
						do
						{
							try																//try to take in menu choice
							{
								//display admin main menu
								System.out.println("\n1. Print Report");
								System.out.println("2. Log Out");
								System.out.println("3. Exit");
								
								menuChoice = input.nextInt();
								
								if(menuChoice < 1 || menuChoice > 3)						//if menu choice not within range
									System.out.println("Enter an valid option");			//display error message
								else														//else input must be valid
									break;													//break from loop
							}
							catch(InputMismatchException e)
							{
								System.out.println("Invalid Input");						//invalid input
								input.next();												//prevents an infinite loop
							}
						}
						while(true);
						
						
						switch(menuChoice)
						{
						case 1:						//display the formatted report to console
							adminReport(audOne, audTwo, audThree);
							System.out.println("");
							break;
							
						case 2:						//return to starting point
							break;
							
						case 3:						//update auditorium files and end program
							
							/*** REWRITE AUDITORIUM ONE ***/
							PrintWriter outputOne = new PrintWriter("A1.txt");	//allows writing to file A1.txt
							TheaterSeat current = audOne.getFirst();		//initialize current pointer to first seat
							
							int rowCount = 0;								//variable keeps track of current row
							while(current != null)
							{
								while(current != null)
								{
									outputOne.print(current.getType());//write char to file
									current = current.getRight();	//move pointer to next seat on row
								}
								
								outputOne.println("");					//new line
								
								rowCount++;							//increment row counter
								int d = 0;
								current = audOne.getFirst();			//set current to first element in grid
								while(d < rowCount)					//move current down until at appropriate row
								{
									current = current.getDown();
									d++;
								}
							}
							outputOne.close();
							
							
							/*** REWRITE AUDITORIUM TWO ***/
							PrintWriter outputTwo = new PrintWriter("A2.txt");	//allows writing to file A1.txt
							current = audTwo.getFirst();		//initialize current pointer to first seat
							
							rowCount = 0;								//variable keeps track of current row
							while(current != null)
							{
								while(current != null)
								{
									outputTwo.print(current.getType());//write char to file
									current = current.getRight();	//move pointer to next seat on row
								}
								
								outputTwo.println("");					//new line
								
								rowCount++;							//increment row counter
								int d = 0;
								current = audTwo.getFirst();			//set current to first element in grid
								while(d < rowCount)					//move current down until at appropriate row
								{
									current = current.getDown();
									d++;
								}
							}
							outputTwo.close();
							
							
							/*** REWRITE AUDITORIUM THREE ***/
							PrintWriter outputThree = new PrintWriter("A3.txt");	//allows writing to file A1.txt
							current = audThree.getFirst();		//initialize current pointer to first seat
							
							rowCount = 0;								//variable keeps track of current row
							while(current != null)
							{
								while(current != null)
								{
									outputThree.print(current.getType());//write char to file
									current = current.getRight();	//move pointer to next seat on row
								}
								
								outputThree.println("");					//new line
								
								rowCount++;							//increment row counter
								int d = 0;
								current = audThree.getFirst();			//set current to first element in grid
								while(d < rowCount)					//move current down until at appropriate row
								{
									current = current.getDown();
									d++;
								}
							}
							outputThree.close();
	
							input.close();
							System.exit(0);
						}
					}while(menuChoice != 2);
					
					//if menu choice is 2 - while loop will exit and back to main login
				}
				else								/***** A USER LOGGED IN *****/
				{
					do								//do while until user logs out
					{
						
						do
						{
							try																//try to take in menu choice
							{
								//display user main menu
								System.out.println("\n1. Reserve Seats");
								System.out.println("2. View Orders");
								System.out.println("3. Update Order");
								System.out.println("4. Display Receipt");
								System.out.println("5. Log Out");
								
								menuChoice = input.nextInt();
								
								if(menuChoice < 1 || menuChoice > 5)						//if menu choice not within range
									System.out.println("Enter an valid choice.");			//display error message
								else														//else input must be valid
									break;													//break from loop
							}
							catch(InputMismatchException e)
							{
								System.out.println("Invalid Input");		//invalid input
								input.next();												//prevents an infinite loop
							}
						}
						while(true);
						
						switch(menuChoice)
						{
						case 1:						//call special create order function
							
							do
							{
								try																//try to take in menu choice
								{
									//display auditorium submenu
									System.out.println("1. Auditorium 1");
									System.out.println("2. Auditroium 2");
									System.out.println("3. Auditorium 3");
									
									menuChoice = input.nextInt();
									
									if(menuChoice < 1 || menuChoice > 3)						//if menu choice not within range
										System.out.println("Enter an available auditorium");	//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid Input");						//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							//now input based on menu choice
							Auditorium audChoice = null;
							
							if(menuChoice == 1)
							{
								audChoice = audOne;
								audNumber = 1;
							}
							else if(menuChoice == 2)
							{
								audChoice = audTwo;
								audNumber = 2;
							}
							else if(menuChoice == 3)
							{
								audChoice = audThree;
								audNumber = 3;
							}
							//display the selected auditorium
							displayAuditorium(audChoice);
							
							//now that we have auditorium - take seat information
							
							int rowChoice, adultQ, childQ, seniorQ;								//prompt for following values and validate
							char startSeat = 'A';
							
							//prompt for row
							//validate - loop until valid
								//valid row == number listed in auditorium display
							
							boolean inRowBounds = true;											//keeps track if choice is in bounds
							
							do
							{
								try {															//try to take valid input from user
									inRowBounds = true;
									
									System.out.print("Reserve tickets on row: ");
									rowChoice = input.nextInt();

									if(rowChoice > Auditorium.getNumRows(audChoice) || rowChoice < 1)	//if row choice is out of bounds
									{	
										System.out.println("Row choice out of bounds");
										inRowBounds = false;									//set boolean tracker to false
									}
									
									if(inRowBounds)												//before breaking make sure selection is in bounds
										break;													//input validated - break in order to exit the do while loop
								}
								catch(InputMismatchException e)									//catch an input exception - anyting but integer entered
								{
									System.out.println("Invalid entry for row number");			//display input error
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							//prompt for starting seat
							//validate - loop until valid
								//valid seat == seat number listed in auditorium display
											
							do 
							{
								try{															//try to take valid input from user
									inRowBounds = true;
									
									System.out.print("Reserve seats starting on letter: ");
									String startEntry = input.next();
									startSeat = startEntry.charAt(0);
									
									String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";					//find last seat in row with this string
									char lastSeat = abc.charAt(Auditorium.getNumSeats(audChoice) - 1);//calculate last letter in row - char at number of seats - 1

									if(startSeat > lastSeat || startSeat < 'A' || startEntry.length() > 1)		//if seat choice is out of bounds
										System.out.println("Seat choice out of bounds");		//print error message
									else														//selection must be in bounds
										break;													//input validated - break in order to exit the do while loop
								}
								catch(InputMismatchException e)									//catch an input exception - anything but char entered
								{
									System.out.println("Invalid entry for starting seat");		//display input error
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							
							//prompt for number of adult tickets
							//validate = loop until valid
								//valid ticket num = num >= 0
											
							do
							{
								try																//try to take in ticket quantity
								{						
									System.out.print("Adult Tickets: ");
									adultQ = input.nextInt();
									
									if(adultQ < 0)												//if ticket quantity entered is not positive
										System.out.println("Enter a positive number");			//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid entry for ticket amount");		//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							
							//prompt for number of child tickets
							//validate = loop until valid
								//valid ticket num = num >= 0
							
							do
							{
								try																//try to take in ticket quantity
								{						
									System.out.print("Child Tickets: ");
									childQ = input.nextInt();
									
									if(childQ < 0)												//if ticket quantity entered is not positive
										System.out.println("Enter a positive number");			//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid entry for ticket amount");		//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							
							//prompt for number of senior tickets
							//validate = loop until valid
								//valid ticket num = num >= 0
							do
							{
								try																//try to take in ticket quantity
								{
									System.out.print("Senior Tickets: ");
									seniorQ = input.nextInt();
									
									if(seniorQ < 0)												//if ticket quantity entered is not positive
										System.out.println("Enter a positive number");			//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid entry for ticket amount");		//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							System.out.println("");
							
							
							//now create order specified by user input
							//make sure seats are available and are not already reserved
							//if seats not available - find best available
							
							int totalQ = adultQ + childQ + seniorQ;				//total quantity of tickets to be reserved
							int seatNum = startSeat - 65;						//convert starting seat char into a useable integer
							boolean allSeatsAvailable = true;					//becomes false if one or more seat is not available
							
							for(int i = 0; i < totalQ; i++)						//for loop checks each seat in selection to see if unoccupied
							{
								if(!(isSeatEmpty(audChoice, rowChoice - 1, seatNum)))	//function returns FALSE if seat is occupied so check for false
								{	
									allSeatsAvailable = false;					//one or more seats unavailable
									break;										//if unable to reserve break from for loop
								}
								seatNum++;										//go to next seat in the row
							}
							
							TheaterSeat best = bestAvailable(audChoice, totalQ);				//calculate the best available starting seat

							if(!allSeatsAvailable)											//if not all seats available - find best available
							{
								best = bestAvailable(audChoice, totalQ);				//calculate the best available starting seat
								
								System.out.println("Selected Seats not Available");
								System.out.print("Best Available Starting Seat: ");			//display best available starting seat
								System.out.print(best.getRow() + 1 + " " + best.getSeat());	//display the best row and seat letter
								System.out.println("");

								char reserve = 'Y';
								
								do
								{
									try
									{
										System.out.print("Order Seats (Y/N): ");						//ask if would like to reserve best available
										String rString = input.next();									//get line from user
										reserve = rString.charAt(0);									//take char input at index 0
										
										if(reserve != 'Y' && reserve != 'N' || rString.length() > 1)	//make sure input is valid if inputed a string
											System.out.println("Please enter a single Y or N response");

										else
											break;														//else - break and exit the loop
									}
									catch(InputMismatchException e)										//catch any non-string input
									{
										System.out.println("Invalid entry for order seats");			//output invalid entry line
										input.next();													//prevents infinite loop
									}
								}
								while(true);
								
								if(reserve == 'Y')														//if reserve char entered as yes
								{
									//reserve seats and add an order
									reserveSeats(audChoice, best.getRow(), best.getSeat(), adultQ, childQ, seniorQ);	//reserve seats starting at best
									Values val = addOrder(audNumber, map, username, audChoice, best.getRow(), best.getSeat(), adultQ, childQ, seniorQ);
									map.put(username, val);
									System.out.println("Best Available Seats Ordered\n");
								}
							}
							
							else																		//else seats are available
							{
								//reserve seats and add an order
								reserveSeats(audChoice, rowChoice - 1, startSeat, adultQ, childQ, seniorQ);	//reserve entered seats
								Values val = addOrder(audNumber, map, username, audChoice, rowChoice - 1, startSeat, adultQ, childQ, seniorQ);
								map.put(username, val);
								System.out.println("Entered Seats Have Been Ordered\n");				//print confirmation message
							}
							break;
							
						case 2:						//call display orders function
							
							if(audNumber == 1)
								audChoice = audOne;
							else if (audNumber == 2)
								audChoice = audTwo;
							else
								audChoice = audThree;
							
							displayOrders(map, username);
							break;
							
						case 3:						//call update order function
							int orderChoice = 0;
							if(audNumber == 1)
								audChoice = audOne;
							else if (audNumber == 2)
								audChoice = audTwo;
							else
								audChoice = audThree;
							
							displayOrders(map, username);
							
							do
							{
								try																//try to take in order choice
								{
									System.out.print("Select Order: ");
									
									orderChoice = input.nextInt();
									
									if(orderChoice < 1 || orderChoice > (map.get(username)).getOrder().getCount())
										System.out.println("Enter an valid order");			//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid Input");						//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							do
							{
								try																//try to take in menu choice
								{
									//print the update order menu
									System.out.println("");
									System.out.println("1. Add tickets to order");
									System.out.println("2. Delete tickets from order");
									System.out.println("3. Cancel Order");
									
									menuChoice = input.nextInt();
									
									if(menuChoice < 1 || menuChoice > 3)						//if menu choice not within range
										System.out.println("Enter an valid option");			//display error message
									else														//else input must be valid
										break;													//break from loop
								}
								catch(InputMismatchException e)
								{
									System.out.println("Invalid Input");						//invalid input
									input.next();												//prevents an infinite loop
								}
							}
							while(true);
							
							//if 1 call add to order function
							if(menuChoice == 1)
							{
								
								//display the selected auditorium
								displayAuditorium(audChoice);
								
								//now that we have auditorium - take seat information
								startSeat = 'A';
								
								//prompt for row
								//validate - loop until valid
									//valid row == number listed in auditorium display
								
								inRowBounds = true;											//keeps track if choice is in bounds
								
								do
								{
									try {															//try to take valid input from user
										inRowBounds = true;
										
										System.out.print("Reserve tickets on row: ");
										rowChoice = input.nextInt();

										if(rowChoice > Auditorium.getNumRows(audChoice) || rowChoice < 1)	//if row choice is out of bounds
										{	
											System.out.println("Row choice out of bounds");
											inRowBounds = false;									//set boolean tracker to false
										}
										
										if(inRowBounds)												//before breaking make sure selection is in bounds
											break;													//input validated - break in order to exit the do while loop
									}
									catch(InputMismatchException e)									//catch an input exception - anyting but integer entered
									{
										System.out.println("Invalid entry for row number");			//display input error
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								//prompt for starting seat
								//validate - loop until valid
									//valid seat == seat number listed in auditorium display
												
								do 
								{
									try{															//try to take valid input from user
										inRowBounds = true;
										
										System.out.print("Reserve seats starting on letter: ");
										String startEntry = input.next();
										startSeat = startEntry.charAt(0);
										
										String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";					//find last seat in row with this string
										char lastSeat = abc.charAt(Auditorium.getNumSeats(audChoice) - 1);//calculate last letter in row - char at number of seats - 1

										if(startSeat > lastSeat || startSeat < 'A' || startEntry.length() > 1)		//if seat choice is out of bounds
											System.out.println("Seat choice out of bounds");		//print error message
										else														//selection must be in bounds
											break;													//input validated - break in order to exit the do while loop
									}
									catch(InputMismatchException e)									//catch an input exception - anything but char entered
									{
										System.out.println("Invalid entry for starting seat");		//display input error
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								
								//prompt for number of adult tickets
								//validate = loop until valid
									//valid ticket num = num >= 0
												
								do
								{
									try																//try to take in ticket quantity
									{						
										System.out.print("Adult Tickets: ");
										adultQ = input.nextInt();
										
										if(adultQ < 0)												//if ticket quantity entered is not positive
											System.out.println("Enter a positive number");			//display error message
										else														//else input must be valid
											break;													//break from loop
									}
									catch(InputMismatchException e)
									{
										System.out.println("Invalid entry for ticket amount");		//invalid input
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								
								//prompt for number of child tickets
								//validate = loop until valid
									//valid ticket num = num >= 0
								
								do
								{
									try																//try to take in ticket quantity
									{						
										System.out.print("Child Tickets: ");
										childQ = input.nextInt();
										
										if(childQ < 0)												//if ticket quantity entered is not positive
											System.out.println("Enter a positive number");			//display error message
										else														//else input must be valid
											break;													//break from loop
									}
									catch(InputMismatchException e)
									{
										System.out.println("Invalid entry for ticket amount");		//invalid input
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								
								//prompt for number of senior tickets
								//validate = loop until valid
									//valid ticket num = num >= 0
								do
								{
									try																//try to take in ticket quantity
									{
										System.out.print("Senior Tickets: ");
										seniorQ = input.nextInt();
										
										if(seniorQ < 0)												//if ticket quantity entered is not positive
											System.out.println("Enter a positive number");			//display error message
										else														//else input must be valid
											break;													//break from loop
									}
									catch(InputMismatchException e)
									{
										System.out.println("Invalid entry for ticket amount");		//invalid input
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								System.out.println("");
								
								
								//now create order specified by user input
								//make sure seats are available and are not already reserved
								//if seats not available - find best available
								
								totalQ = adultQ + childQ + seniorQ;				//total quantity of tickets to be reserved
								seatNum = startSeat - 65;						//convert starting seat char into a useable integer
								allSeatsAvailable = true;					//becomes false if one or more seat is not available
								
								for(int i = 0; i < totalQ; i++)						//for loop checks each seat in selection to see if unoccupied
								{
									if(!(isSeatEmpty(audChoice, rowChoice - 1, seatNum)))	//function returns FALSE if seat is occupied so check for false
									{	
										allSeatsAvailable = false;					//one or more seats unavailable
										break;										//if unable to reserve break from for loop
									}
									seatNum++;										//go to next seat in the row
								}
								
								if(!allSeatsAvailable)											//if not all seats available - find best available
								{
									System.out.println("Selected seats not available\n");
									continue;
								}
								
								else																		//else seats are available
								{
									//reserve seats and add an order
									reserveSeats(audChoice, rowChoice - 1, startSeat, adultQ, childQ, seniorQ);	//reserve entered seats
									Values val = addTo(orderChoice, audNumber, map, username, audChoice, rowChoice - 1, startSeat, adultQ, childQ, seniorQ);
									map.put(username, val);
									System.out.println("Entered Seats Have Been Added to Order\n");				//print confirmation message
								}
							}
							
							//if 2 remove tickets from order
							else if(menuChoice == 2)
							{
								
								startSeat = 'A';
								
								//prompt for row
								//validate - loop until valid
									//valid row == number listed in auditorium display
								
								inRowBounds = true;											//keeps track if choice is in bounds
								
								do
								{
									try {															//try to take valid input from user
										inRowBounds = true;
										
										System.out.print("Remove ticket on row: ");
										rowChoice = input.nextInt();

										if(rowChoice > Auditorium.getNumRows(audChoice) || rowChoice < 1)	//if row choice is out of bounds
										{	
											System.out.println("Row choice out of bounds");
											inRowBounds = false;									//set boolean tracker to false
										}
										
										if(inRowBounds)												//before breaking make sure selection is in bounds
											break;													//input validated - break in order to exit the do while loop
									}
									catch(InputMismatchException e)									//catch an input exception - anyting but integer entered
									{
										System.out.println("Invalid entry for row number");			//display input error
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								//prompt for starting seat
								//validate - loop until valid
									//valid seat == seat number listed in auditorium display
												
								do 
								{
									try{															//try to take valid input from user
										inRowBounds = true;
										
										System.out.print("Remove seats starting on letter: ");
										String startEntry = input.next();
										startSeat = startEntry.charAt(0);
										
										String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";					//find last seat in row with this string
										char lastSeat = abc.charAt(Auditorium.getNumSeats(audChoice) - 1);//calculate last letter in row - char at number of seats - 1

										if(startSeat > lastSeat || startSeat < 'A' || startEntry.length() > 1)		//if seat choice is out of bounds
											System.out.println("Seat choice out of bounds");		//print error message
										else														//selection must be in bounds
											break;													//input validated - break in order to exit the do while loop
									}
									catch(InputMismatchException e)									//catch an input exception - anything but char entered
									{
										System.out.println("Invalid entry for seat");		//display input error
										input.next();												//prevents an infinite loop
									}
								}
								while(true);
								
								boolean seatIsValid = false;
								//now we have row number and seat letter to remove
								//obtain the linked orders list for that user key
								LinkedOrders temp = (map.get(username)).getOrder();
								BaseOrder nodeChoice = temp.getNode(orderChoice - 1);
								
								int s = nodeChoice.getSeatListSize();	//list size
								
								for(int i = 0; i < s; i++)
								{
									//if seat and row are found in array list
									if((nodeChoice.getSeatList().get(i).getRow() == rowChoice - 1)
											&& nodeChoice.getSeatList().get(i).getSeat() == startSeat)
									{
										seatIsValid = true;
										
										//mark seat as unreserved in auditorium
										TheaterSeat currSeat = audChoice.getFirst();	//initialize the current seat pointer to first seat
																				
										while(currSeat.getRow() < nodeChoice.getSeatList().get(s - 1).getRow())			//while loop - move current to correct row
											currSeat = currSeat.getDown();		//move current down a node
										
										while(currSeat.getSeat() < temp.getNode(orderChoice - 1).getSeatList().get(0).getSeat())			//while loop - move current to correct seat column
											currSeat = currSeat.getRight();		//move current to the right a node
										
										currSeat.setType('.');				//set type to '.'
										currSeat.setReserved(false);		//mark as reserved
										currSeat = currSeat.getRight();		//move pointer to the right
										
										//remove the seat from the list order
										nodeChoice.getSeatList().remove(i);
										
										break;
									}
								}
								
								if(!seatIsValid)
								{
									System.out.println("Seat does not exist in order.");
								}
							}
							
							//if 3 delete the entire order
							else if(menuChoice == 3)
							{
								//obtain the linked orders list for that user key
								LinkedOrders temp = (map.get(username)).getOrder();
								BaseOrder nodeChoice = temp.getNode(orderChoice - 1);
								
								//now mark all seats as empty in the auditorium object
								TheaterSeat currSeat = audChoice.getFirst();	//initialize the current seat pointer to first seat
								
								int s = nodeChoice.getSeatListSize();	//list size
								
								while(currSeat.getRow() < nodeChoice.getSeatList().get(s - 1).getRow())			//while loop - move current to correct row
									currSeat = currSeat.getDown();		//move current down a node
								
								while(currSeat.getSeat() < temp.getNode(orderChoice - 1).getSeatList().get(0).getSeat())			//while loop - move current to correct seat column
									currSeat = currSeat.getRight();		//move current to the right a node
								
								
								for(int a = 0; a < s; a++)				//for loop - set empty seats
								{
									currSeat.setType('.');				//set type to '.'
									currSeat.setReserved(false);			//mark as reserved
									currSeat = currSeat.getRight();		//move pointer to the right
								}
								
								//delete the node within temp at the order specified
								temp.remove(orderChoice - 1);
								
								//now that have updated temp - put temp back into the value for that user
								Values val = new Values(password, temp);
								
								//now put val into the hashmap
								map.put(username, val);
							}
							break;
							
						case 4:						//create formatted receipt for the user
							if(audNumber == 1)
								audChoice = audOne;
							else if (audNumber == 2)
								audChoice = audTwo;
							else
								audChoice = audThree;
							
							displayReceipt(map, username, audChoice);
							break;
							
						case 5:						//return to starting point
							break;
						}
					}while(menuChoice != 5);
					
				//if menu choice is 5 - while loop will exit and back to main login
				}
			}
		}while(true);
	}
	
	/*
	 * function adds an order to the user in the hashmap
	 * takes the LinkedOrder that is stored inside the Values object for that user
	 * Adds a node to that LinkedOrder object containing:
	 * 		- ArrayList of seats
	 * 		- numA
	 * 		- numC
	 * 		- numS
	 * returns an new value to replace the old one in the hashmap for that username
	 */
	public static Values addOrder(int audNumber, HashMap<String, Values> map, String username, 
			Auditorium aud, int row, char seat, int adultQ, int childQ, int seniorQ)
	{
		//obtain the linked orders list for that user key
		LinkedOrders temp = (map.get(username)).getOrder();
		
		//get the starting seat in the auditorium
		TheaterSeat current = aud.getFirst();	//initialize the current seat pointer to first seat
		
		while(current.getRow() < row)			//while loop - move current to correct row
			current = current.getDown();		//move current down a node
		
		while(current.getSeat() < seat)			//while loop - move current to correct seat column
			current = current.getRight();		//move current to the right a node
		
		//now current theaterseat pointer is at starting seat
		//add a node to the linked orders list
		temp.add(audNumber, aud, current, row, seat, adultQ, childQ, seniorQ);
		
		//get password for user
		String password = (map.get(username)).getPassword();
		
		//create new Values object and return it
		return new Values(password, temp);
	}
	
	/*
	 * function adds seats to a current order
	 */
	public static Values addTo(int orderNum, int audNumber, HashMap<String, Values> map, String username, 
			Auditorium aud, int row, char seat, int adultQ, int childQ, int seniorQ)
	{
		//obtain the linked orders list for that user key
		LinkedOrders temp = (map.get(username)).getOrder();
		
		//get the starting seat in the auditorium
		TheaterSeat current = aud.getFirst();	//initialize the current seat pointer to first seat
		
		while(current.getRow() < row)			//while loop - move current to correct row
			current = current.getDown();		//move current down a node
		
		while(current.getSeat() < seat)			//while loop - move current to correct seat column
			current = current.getRight();		//move current to the right a node
		
		//now current theaterseat pointer is at starting seat
		//add items to current node
		
		BaseOrder edit = temp.getNode(orderNum - 1);
		edit.setAdult(temp.getNode(orderNum - 1).getAdult() + adultQ);
		edit.setChild(temp.getNode(orderNum - 1).getChild() + childQ);
		edit.setSenior(temp.getNode(orderNum - 1).getSenior() + seniorQ);
		
		
		TheaterSeat point = aud.getFirst();	//initialize the current seat pointer to first seat
		
		while(point.getRow() < row)			//while loop - move current to correct row
			point = point.getDown();		//move current down a node
		
		while(point.getSeat() < seat)			//while loop - move current to correct seat column
			point = point.getRight();		//move current to the right a node
		
		int totalQ = adultQ + childQ + seniorQ;		//totalQ used to determine how many seats there will be in order
		
		for(int i = 0; i < totalQ; i++)
		{
			edit.getSeatList().add(point);					//add current seat to the reservation
			point = point.getRight();			//move to next seat in order
		}
 
		
		//get password for user
		String password = (map.get(username)).getPassword();
		
		//create new Values object and return it
		return new Values(password, temp);
	}
	
	
	/*
	 * function displays orders for a given user
	 * formatted output
	 * returns void
	 */
	public static void displayOrders(HashMap<String, Values> map, String username)
	{
		//get the linked order list for that user
		LinkedOrders orders = (map.get(username)).getOrder();
		
		//traverse the linked list - printing each node in a formatted output
		/*
		 * Auditorium	Seats	Adult Tickets	Child Tickets	Senior Tickets
		 * 1			E1E2E3	1				0				2
		 * 2			B5B6	0				0				2
		 */
		
		int orderNum = 0;
		
		//print column labels
		System.out.println("");
		System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s",
				"Order Number","Auditorium #", "Seats", "Adult Tickets", "Child Tickets", "Senior Tickets");
		
		BaseOrder curr = orders.getHead();
		while(curr != null)					//while not at end of linked list
		{
			//get values for current order node
			int audNum = curr.getAudNum();
			int numA = curr.getAdult();
			int numC = curr.getChild();
			int numS = curr.getSenior();
			String listStr = "";
			
			//get the array list of the seats for the current order
			ArrayList<TheaterSeat> seatList = curr.getSeatList();
			
			//put the seatList array list into a string
			for(int i = 0; i < seatList.size(); i++)
			{
				TheaterSeat temp = seatList.get(i);
				int row = temp.getRow() + 1;			//get the row the seat is on
				char seat = temp.getSeat();				//get the seat letter
				
				//now put into a temp string of format: E1, B7, A2, ect.
				String tempStr = "" + row + seat + " ";
				
				//add tempStr to line string
				listStr = listStr + tempStr;
			}
			
			//now print values for order
			if(audNum != 0)
			{
				System.out.println("");
				System.out.printf("%-25d%-25d%-25s%-25d%-25d%-25d",
						orderNum, audNum, listStr, numA, numC, numS);
			}
			
			orderNum++;
			curr = curr.getNext();
		}
		
		System.out.println("");
	}
	
	/*
	 * function allows update to the orders
	 */
	
	
	
	
	
	
	/*
	 * function displays customer receipt
	 * loop through all orders
	 * calculate total cost for all orders and display
	 */
	public static void displayReceipt(HashMap<String, Values> map, String username, Auditorium aud)
	{
		//get the linked order list for that user
		LinkedOrders orders = (map.get(username)).getOrder();
		
		//traverse the linked list - printing each node in a formatted output
		/*
		 * Auditorium	Seats	Adult Tickets	Child Tickets	Senior Tickets
		 * 1			E1E2E3	1				0				2
		 * 2			B5B6	0				0				2
		 */
		DecimalFormat salesOutput = new DecimalFormat("#.00");	//will output sales as two decimals
		
//		int currAud, adultQ, childQ, seniorQ;
		double orderCost = 0.0;
		double totalPrice = 0.0;
		int orderNum = 0;
		
		//print column labels
		System.out.println("");
		System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s%-25s",
				"Order Number","Auditorium #", "Seats", "Adult Tickets", "Child Tickets", "Senior Tickets", "Order Cost");
		
		BaseOrder curr = orders.getHead();
		while(curr != null)					//while not at end of linked list
		{
			//get values for current order node
			int audNum = curr.getAudNum();
			int numA = curr.getAdult();
			int numC = curr.getChild();
			int numS = curr.getSenior();
			String listStr = "";
			
			//get the array list of the seats for the current order
			ArrayList<TheaterSeat> seatList = curr.getSeatList();
			
			//put the seatList array list into a string
			for(int i = 0; i < seatList.size(); i++)
			{
				TheaterSeat temp = seatList.get(i);
				int row = temp.getRow() + 1;			//get the row the seat is on
				char seat = temp.getSeat();				//get the seat letter
				
				//now put into a temp string of format: E1, B7, A2, ect.
				String tempStr = "" + row + seat + " ";
				
				//add tempStr to line string
				listStr = listStr + tempStr;
			}
			
			//now print values for order
			if(audNum != 0)
			{
				orderCost = (10.0 * numA) + (5.0 * numC) + (7.5 * numS);
				totalPrice = totalPrice + orderCost;
				
				System.out.println("");
				System.out.printf("%-25d%-25d%-25s%-25d%-25d%-25d",
						orderNum, audNum, listStr, numA, numC, numS);
				System.out.print("$" + salesOutput.format(orderCost));
			}
			
			orderNum++;
			curr = curr.getNext();
		}
		
		System.out.println("\n");
		System.out.printf("%-25s", "Total Cost:");
		System.out.print("$" + salesOutput.format(totalPrice));
		System.out.println("");
	}
	
	
	/*
	 * function creates a hash map
	 * userdb.dat file is read and data is filed into a hash map
	 * returns hash map
	 */
	public static HashMap<String, Values> createHash() throws FileNotFoundException
	{
		//create hash map and then fill the map
		HashMap<String, Values> map = new HashMap<>();
		
		Scanner users = new Scanner(new File("userdb.dat"));
		
		while(users.hasNextLine())
		{
			String temp = users.nextLine();		//get the line of the file
			String [] sep = temp.split("\\s+");	//split line by space
			String username = sep[0];			//zero index is username
			String password = sep[1];			//first index is password
			
			//put username and value with empty order into the hashmap
			map.put(username, new Values(password, new LinkedOrders()));
		}
		users.close();
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	 * differs from checkSeatAvail
	 * this accepts auditorium, row, and seat as an argument
	 * does not read from char array like check seat avail
	 * use this function in main to prevent changing previous reservations
	 */
	public static boolean isSeatEmpty(Auditorium aud, int row, int seat)
	{
		TheaterSeat current = aud.getFirst();
		
		while(current.getRow() < row)			//while loop - move current to correct row
		{
			current = current.getDown();		//move current down a node
		}
		
		
		while(current.getSeat() - 65 < seat)	//while loop - move current to correct seat column
		{
			current = current.getRight();		//move current to the right a node		
		}
		
		char temp = current.getType();			//get the seat type at the current node
		
		if(temp != '.')			//if seat occupied return false
		{
			return false;
		}
		
		return true;			//otherwise seat is empty return true
	}
	
	/*
	 * if wanted seats are not available to reserve
	 * this function will find the best available seats using pythag. thm.
	 * and return the starting TheaterSeat for the selection
	 * parameters are Auditorium object and ticket quantity
	 */
	public static TheaterSeat bestAvailable(Auditorium aud, int quantity)
	{
		double leastDistance = 200;				//keeps track of smallest distance - initialize to 200
		
		TheaterSeat bestAvail = null;			
		TheaterSeat curr =  aud.getFirst();		//set current pointer
		TheaterSeat nextR = aud.getFirst();		//next row pointer
		
		int numRows = 0;						//track number of rows
		int numSeats = 0;						//track number of seats
		
		while(curr != null)				//while loop if current does not equal null
		{
			curr = curr.getDown();		//go down a row
			numRows++;					//increment number of rows
		}
		
		curr = aud.getFirst();
		
		while(curr != null)				//while loop if current does not equal null
		{
			curr = curr.getRight();		//go right a seat
			numSeats++;					//increment number of seats
		}
		
		curr = aud.getFirst();
		
		for(int i = 0; i < numRows; i++)						//loop through rows
		{
			for(int j = 0; j < (numSeats - quantity + 1); )		//loop through seats
			{
				boolean avail = true;							//marks if seat is available
				TheaterSeat beginCount = curr;					//set first seat to current pointer
				
				for(int c = 0; c < quantity; c++)				//check if quantity can fit onto a row
				{
					char seatType = curr.getType();
					if(seatType != '.')							//if the current seat is not available
					{
						beginCount = null;			//set first to null
						avail = false;				//mark as not available
						
						curr = curr.getRight();		//set current to right seat
						j++;						//move to next seat
						
						break;						//exit the loop - section not valid
					}
					curr = curr.getRight();
					j++;
				}
				
				//since the seats are available - find the least distance
				//obtain distance from center using pythag. thm.
				//then compare the leastDistance to calculated dist
				if(avail == true)
				{
					double hDist = Math.abs((j + (quantity - 1) / 2) - ((numSeats + 1) / 2));	//calculate the seat distance
					double vDist = Math.abs(((numRows - 1) / 2.0) - i);							//calculate the row distance
					
					double dist = Math.sqrt((Math.pow(hDist, 2) + Math.pow(vDist, 2)));			//calculate diag. distance
					
					if(dist < leastDistance)		//if new distance is smaller than leastDist
					{
						leastDistance = dist;		//set least distance to the current distance	
						bestAvail = beginCount;		//set best available seat to the start of this quantity being check
					}
				}
			}
			
			curr = nextR.getDown();		//move current pointer down a row
			nextR = nextR.getDown();	//move next row down a row
		}
		
		return bestAvail;				//return the best available seat
	}
	
	
	
	
	/*
	 * Displays the current auditorium
	 * with labeled rows and seat columns
	 * parameter is an Auditorium object
	 */
	public static void displayAuditorium(Auditorium aud)
	{
		int numSeats = Auditorium.getNumSeats(aud);	//get number of seats in row
		
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";	//Will reduce string if needed
		abc = abc.substring(0, numSeats);			//cut string to number of rows
		System.out.println("  " + abc);				//print column heading
		
		int rowNum = 1;								//print before each row, increment before next row in for loop
		
		TheaterSeat current = aud.getFirst();
	
		while(current != null)						//while pointer not at null - row
		{
			System.out.print(rowNum + " ");			//print current row number
			
			while(current != null)					//while pointer not at null - column
			{
				if(current.getType() == '.')		//if period print a period
					System.out.print('.');
				else								//else print a number sign
					System.out.print("#");

				current = current.getRight();		//move pointer to right
			}
			
			System.out.println("");					//new line
			
			rowNum++;								//increment row header
			int d = 0;
			current = aud.getFirst();				//set current to first element in grid
			while(d < rowNum - 1)					//move current down until at appropriate row
			{
				current = current.getDown();
				d++;
			}
		}
		
		System.out.println("");						//new line
	}

	/*
	 * reserves seats specified by user
	 * parameters are auditorium, row number, starting seat, and quantity of each type (A, C, S)
	 */
	public static void reserveSeats(Auditorium aud, int row, char seat, int adultQ, int childQ, int seniorQ)
	{
		TheaterSeat current = aud.getFirst();	//initialize the current seat pointer to first seat
		
		while(current.getRow() < row)			//while loop - move current to correct row
			current = current.getDown();		//move current down a node
		
		while(current.getSeat() < seat)			//while loop - move current to correct seat column
			current = current.getRight();		//move current to the right a node
		
		for(int a = 0; a < adultQ; a++)			//for loop - set adult seats
		{
			current.setType('A');				//set type to A
			current.setReserved(true);			//mark as reserved
			current = current.getRight();		//move pointer to the right
		}
		
		for(int c = 0; c < childQ; c++)			//for loop - set child seats
		{
			current.setType('C');				//set type to C
			current.setReserved(true);			//mark as reserved
			current = current.getRight();		//move pointer to the right
		}
		
		for(int s = 0; s < seniorQ; s++)		//for loop - set senior seats
		{
			current.setType('S');				//set type to S
			current.setReserved(true);			//mark as reserved
			current = current.getRight();		//move pointer to the right
		}
	}
	
	/*
	 * function displays formatted admin report to console
	 * parameters are each of the three auditoriums
	 * returns nothing
	 */
	public static void adminReport(Auditorium audOne, Auditorium audTwo, Auditorium audThree)
	{
		DecimalFormat salesOutput = new DecimalFormat("#.00");	//will output sales as two decimals
		
		//print column labels
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
				"Auditorium #", "Open Seats", "Reserved Seats", "Adult Seats", "Child Seats", "Senior Seats", "Tickets Sales");
		
		int totalO = 0, totalRes = 0, totalA = 0, totalC = 0, totalS = 0;
		double totalSales = 0.0;
		
		int openSeats = 0, reservedSeats = 0, aSold = 0, cSold = 0, sSold = 0;
		double ticketSales = 0.0;
		
		char tempChar;							//compare to char at each node to determine type of ticket
		
		/*************************** AUDITORIUM ONE ***************************/
		
		TheaterSeat current = audOne.getFirst();
		
		int rowCount = 0;
		while(current != null)
		{
			while(current != null)
			{
				tempChar = current.getType();	//get char at current seat
				
				if(tempChar == 'A')				//if adult ticket
				{
					aSold++;
					reservedSeats++;
				}
				else if(tempChar == 'C')		//if child ticket
				{
					cSold++;
					reservedSeats++;
				}
				else if(tempChar == 'S')		//if senior ticket
				{
					sSold++;
					reservedSeats++;
				}
				else if(tempChar == '.')
					openSeats++;
				current = current.getRight();	//move pointer to next seat on row
			}
			
			rowCount++;							//increment row counter
			int d = 0;
			current = audOne.getFirst();		//set current to first element in grid
			while(d < rowCount)					//move current down until at appropriate row
			{
				current = current.getDown();
				d++;
			}
		}
		
		ticketSales = (aSold * 10.0) + (cSold * 5.0) + (sSold * 7.5);	//calculate the total sales
		
		//calculate the running total of all three auditoriums
		totalO += openSeats;
		totalRes += reservedSeats;
		totalA += aSold;
		totalC += cSold;
		totalS += sSold;
		totalSales += ticketSales;
		
		//print auditorium one information
		System.out.println("");
		System.out.printf("%-20s%-20d%-20d%-20d%-20d%-20d",
				"Auditorium 1", openSeats, reservedSeats, aSold, cSold, sSold);
		System.out.print("$" + salesOutput.format(ticketSales));
		
		//reset auditorium specific variables to zero
		openSeats = 0;
		reservedSeats = 0;
		aSold = 0;
		cSold = 0;
		sSold = 0;
		ticketSales = 0.0;
		
		/*************************** AUDITORIUM TWO ***************************/
		
		current = audTwo.getFirst();
		
		rowCount = 0;
		while(current != null)
		{
			while(current != null)
			{
				tempChar = current.getType();	//get char at current seat
				
				if(tempChar == 'A')				//if adult ticket
				{
					aSold++;
					reservedSeats++;
				}
				else if(tempChar == 'C')		//if child ticket
				{
					cSold++;
					reservedSeats++;
				}
				else if(tempChar == 'S')		//if senior ticket
				{
					sSold++;
					reservedSeats++;
				}
				else if(tempChar == '.')
					openSeats++;
				current = current.getRight();	//move pointer to next seat on row
			}
			
			rowCount++;							//increment row counter
			int d = 0;
			current = audTwo.getFirst();		//set current to first element in grid
			while(d < rowCount)					//move current down until at appropriate row
			{
				current = current.getDown();
				d++;
			}
		}
		
		ticketSales = (aSold * 10.0) + (cSold * 5.0) + (sSold * 7.5);	//calculate the total sales
		
		//calculate the running total of all three auditoriums
		totalO += openSeats;
		totalRes += reservedSeats;
		totalA += aSold;
		totalC += cSold;
		totalS += sSold;
		totalSales += ticketSales;
		
		//print auditorium two information
		System.out.println("");
		System.out.printf("%-20s%-20d%-20d%-20d%-20d%-20d",
				"Auditorium 2", openSeats, reservedSeats, aSold, cSold, sSold);
		System.out.print("$" + salesOutput.format(ticketSales));
		
		//reset auditorium specific variables to zero
		openSeats = 0;
		reservedSeats = 0;
		aSold = 0;
		cSold = 0;
		sSold = 0;
		ticketSales = 0.0;
		
		/*************************** AUDITORIUM THREE ***************************/
		
		current = audThree.getFirst();
		
		rowCount = 0;
		while(current != null)
		{
			while(current != null)
			{
				tempChar = current.getType();	//get char at current seat
				
				if(tempChar == 'A')				//if adult ticket
				{
					aSold++;
					reservedSeats++;
				}
				else if(tempChar == 'C')		//if child ticket
				{
					cSold++;
					reservedSeats++;
				}
				else if(tempChar == 'S')		//if senior ticket
				{
					sSold++;
					reservedSeats++;
				}
				else if(tempChar == '.')
					openSeats++;
				current = current.getRight();	//move pointer to next seat on row
			}
			
			rowCount++;							//increment row counter
			int d = 0;
			current = audThree.getFirst();		//set current to first element in grid
			while(d < rowCount)					//move current down until at appropriate row
			{
				current = current.getDown();
				d++;
			}
		}
		
		ticketSales = (aSold * 10.0) + (cSold * 5.0) + (sSold * 7.5);	//calculate the total sales
		
		//calculate the running total of all three auditoriums
		totalO += openSeats;
		totalRes += reservedSeats;
		totalA += aSold;
		totalC += cSold;
		totalS += sSold;
		totalSales += ticketSales;
		
		//print auditorium three information
		System.out.println("");
		System.out.printf("%-20s%-20d%-20d%-20d%-20d%-20d",
				"Auditorium 3", openSeats, reservedSeats, aSold, cSold, sSold);
		System.out.print("$" + salesOutput.format(ticketSales));
		
		//reset auditorium specific variables to zero
		openSeats = 0;
		reservedSeats = 0;
		aSold = 0;
		cSold = 0;
		sSold = 0;
		ticketSales = 0.0;
		
		/*************************** TOTAL INFORMATION ***************************/
		System.out.println("");
		System.out.printf("%-20s%-20d%-20d%-20d%-20d%-20d",
				"Total", totalO, totalRes, totalA, totalC, totalS);
		System.out.print("$" + salesOutput.format(totalSales));
	}
}
